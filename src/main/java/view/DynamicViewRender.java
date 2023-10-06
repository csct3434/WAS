package view;

import application.controller.ControllerResolver;
import webserver.utils.FileUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicViewRender {
    private static DynamicViewRender instance;

    private DynamicViewRender() {
    }

    public static DynamicViewRender getInstance() {
        if (instance == null) {
            synchronized (ControllerResolver.class) {
                instance = new DynamicViewRender();
            }
        }
        return instance;
    }

    public byte[] render(ModelAndView modelAndView) throws IOException {
        String viewName = modelAndView.getViewName();
        byte[] fileBytes = FileUtils.readFileBytes(viewName);

        return doRender(fileBytes, modelAndView.getModel());
    }

    private byte[] doRender(byte[] fileBytes, Model model) {
        String fileData = new String(fileBytes);

        if(!fileData.startsWith("#{DYNAMIC_RENDER}")) {
            return fileBytes;
        }

        fileData = removeDynamicIndicator(fileData);
        fileData = renderNavigatorBar(model, fileData);

        for (String attributeName : model.getAttributeNames()) {
            Object modelAttribute = model.getAttribute(attributeName);

            if(modelAttribute instanceof List) {
                fileData = renderListAttribute(fileData, modelAttribute);
                continue;
            }
            fileData = renderSingleAttribute(fileData, attributeName, modelAttribute);
        }

        return fileData.getBytes();
    }

    private String removeDynamicIndicator(String fileData) {
        return fileData.replace("#{DYNAMIC_RENDER}\n", "");
    }

    private String renderNavigatorBar(Model model, String fileData) {
        boolean login = model.getLogin();

        if(login) {
            return fileData.replace("#{DISPLAY_ON_LOGIN}", "")
                    .replace("#{DISPLAY_ON_LOGOUT}", "style=\"display: none\"")
                    .replace("${username}", (String) model.getAttribute("username"));
        }
        return fileData.replace("#{DISPLAY_ON_LOGIN}", "style=\"display: none\"")
                    .replace("#{DISPLAY_ON_LOGOUT}", "");
    }

    private String renderListAttribute(String fileData, Object modelAttribute) {
        StringBuilder stringBuilder = new StringBuilder();

        Pattern listIndicatorPattern = Pattern.compile("\\$\\[\\w+:\\s*(.*?)\\]");
        Matcher listIndicatorMatcher = listIndicatorPattern.matcher(fileData);

        while(listIndicatorMatcher.find()) {
            String TEMPLATE_LINE = listIndicatorMatcher.group(1);

            List<?> itemList = (List<?>) modelAttribute;

            for(Object item : itemList) {
                Pattern fieldPattern = Pattern.compile("\\$\\{::(.*?)}");
                Matcher fieldMatcher = fieldPattern.matcher(TEMPLATE_LINE);

                String RENDERED_LINE = new String(TEMPLATE_LINE);

                while (fieldMatcher.find()) {
                    String fieldName = fieldMatcher.group(1);

                    try {
                        String fieldValue = getFieldValue(item, fieldName);
                        String replacePattern = (new StringBuilder()).append("${::").append(fieldName).append("}").toString();

                        RENDERED_LINE = RENDERED_LINE.replace(replacePattern, fieldValue);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                stringBuilder.append(RENDERED_LINE).append("\n");
            }

            String renderedLine = stringBuilder.toString();

            fileData = listIndicatorMatcher.replaceAll(renderedLine);
        }

        return fileData;
    }

    private String getFieldValue(Object item, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = item.getClass().getDeclaredField(fieldName);
        field.setAccessible(true); // private 필드에 접근하기 위해 설정

        if(field.getType() == int.class) {
            return String.valueOf(field.get(item));
        }
        return (String) field.get(item);
    }

    private String renderSingleAttribute(String fileData, String attributeName, Object modelAttribute) {
        String regexPattern = "\\$\\{" + attributeName + "\\}";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(fileData);

        return matcher.replaceAll((String) modelAttribute);
    }
}
