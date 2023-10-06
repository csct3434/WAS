package db;

import webserver.session.Session;

import java.util.concurrent.ConcurrentHashMap;

public final class SessionDatabase {
    private static final ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    private SessionDatabase() {
    }

    public static String addSession(Session session) {
        sessions.put(session.getSessionId(), session);
        return session.getSessionId();
    }

    public static boolean contains(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    public static String findUserIdBySessionId(String sessionId) {
        if(sessions.containsKey(sessionId)) {
            return sessions.get(sessionId).getUserId();
        }
        return "";
    }
}
