package view;

public class ModelAndView {
    private final String viewName;
    private final Model model;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
        model = new Model();
    }

    public void addAttribute(String name, Object value) {
        model.addAttribute(name, value);
    }

    public String getViewName() {
        return viewName;
    }

    public Model getModel() {
        return model;
    }

    public void setLogin(String username) {
        model.setLogin();
        model.addAttribute("username", username);
    }
}
