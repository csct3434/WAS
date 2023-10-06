package webserver.http;

import java.util.ArrayList;
import java.util.List;

public class Cookie {
    private final List<String> directives;

    public Cookie() {
        this.directives = new ArrayList<>();
    }

    public void add(String directive) {
        directives.add(directive);
    }

    public String get(String name) {
        return directives.stream()
                .map(directive -> directive.split("="))
                .filter(tokens -> tokens.length == 2 && tokens[0].equals(name))
                .findFirst()
                .map(tokens -> tokens[1])
                .orElse("");
    }

    public String getMessage() {
        return String.join("; ", directives);
    }

    public boolean isEmpty() {
        return directives.isEmpty();
    }
}
