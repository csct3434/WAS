package webserver.session;

import java.util.UUID;

public class Session {
    private final String sessionId;
    private final String userId;

    public Session(String userId) {
        this.sessionId = UUID.randomUUID().toString();
        this.userId = userId;
    }

    public boolean verifySessionId(String sessionId) {
        return sessionId.equals(this.sessionId);
    }

    public String getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
