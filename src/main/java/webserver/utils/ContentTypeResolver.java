package webserver.utils;

import java.util.HashMap;
import java.util.Map;

public class ContentTypeResolver {
    private static final Map<String, String> MIME_TYPE = new HashMap<>();

    static {
        MIME_TYPE.put("html", "text/html;charset=utf-8");
        MIME_TYPE.put("css", "text/css");
        MIME_TYPE.put("js", "application/javascript");
        MIME_TYPE.put("png", "image/png");
        MIME_TYPE.put("eot", "application/vnd.ms-fontobject");
        MIME_TYPE.put("svg", "image/svg+xml");
        MIME_TYPE.put("ttf", "application/font-sfnt");
        MIME_TYPE.put("woff", "application/font-woff");
        MIME_TYPE.put("woff2", "font/woff2");
        MIME_TYPE.put("ico", "image/x-icon");
        MIME_TYPE.put("jpg", "image/jpeg");
        MIME_TYPE.put("jpeg", "image/jpeg");
    }

    public static String getContentType(String URI) {
        int dotIndex = URI.lastIndexOf(".");
        if(dotIndex != -1 && dotIndex < URI.length() - 1) {
            String extension = URI.substring(dotIndex + 1).toLowerCase();
            return MIME_TYPE.getOrDefault(extension, "application/octet-stream");
        }
        return "application/octet-stream";
    }
}
