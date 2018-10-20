package com.eco.rating.model;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> userMap = new HashMap<>();

    public void add(User user) {
        userMap.put(user.getName(), user);
    }

    public User get(String userName) {
        return userMap.get(userName);
    }
}
