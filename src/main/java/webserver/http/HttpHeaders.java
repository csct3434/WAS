package webserver.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpHeaders {
    private final Map<String, String> headers;

    public HttpHeaders() {
        this.headers = new HashMap<>();
    }

    public String get(String name) {
        if (headers.containsKey(name)) {
            return new String(headers.get(name));
        }
        return "";
    }

    public void put(String name, String value) {
        headers.put(name, value);
    }

    public Set<String> getFieldNames() {
        return headers.keySet();
    }

    public boolean contains(String name) {
        return headers.containsKey(name);
    }
}
