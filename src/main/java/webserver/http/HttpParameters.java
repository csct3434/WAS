package webserver.http;

import java.util.HashMap;
import java.util.Map;

public class HttpParameters {
    private final Map<String, String> parameters;

    public HttpParameters() {
        parameters = new HashMap<>();
    }

    public String get(String name) {
        if (parameters.containsKey(name)) {
            return new String(parameters.get(name));
        }
        return null;
    }

    public void put(String name, String value) {
        parameters.put(name, value);
    }

    public int size() {
        return parameters.size();
    }
}
