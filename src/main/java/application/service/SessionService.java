package application.service;

import db.SessionDatabase;
import webserver.session.Session;

public class SessionService {
    private static SessionService sessionService;

    private SessionService() {
    }

    public static SessionService getInstance() {
        if(sessionService == null) {
            sessionService = new SessionService();
        }
        return sessionService;
    }

    public String create(String userId) {
        return SessionDatabase.addSession(new Session(userId));
    }

    public boolean verifySessionId(String sessionId) {
        return SessionDatabase.contains(sessionId);
    }

    public String findUserId(String sessionId) {
        return SessionDatabase.findUserIdBySessionId(sessionId);
    }
}
