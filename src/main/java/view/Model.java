package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Model {
    private boolean login = false;
    private Map<String, Object> attributes;

    public Model() {
        attributes = new HashMap<>();
    }

    public void addAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return attributes.getOrDefault(name, "");
    }

    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }

    public void setLogin() {
        login = true;
    }

    public boolean getLogin() {
        return login;
    }
}
