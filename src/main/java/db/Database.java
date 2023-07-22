package db;

import model.User;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Database {
    private static ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static void clear() {
        users.clear();
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}
