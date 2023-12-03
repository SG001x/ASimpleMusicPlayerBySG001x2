package com.example.asimplemusicplayerbysg001x2;

import java.util.ArrayList;
import java.util.List;

public class LiteSQLManager {
    private static LiteSQLManager instance;
    private List<User> userList;

    private LiteSQLManager() {
        userList = new ArrayList<>();
    }

    public static LiteSQLManager getInstance() {
        if (instance == null) {
            instance = new LiteSQLManager();
        }
        return instance;
    }

    public boolean registerUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return false; // 用户名已存在
            }
        }

        User newUser = new User(username, password);
        userList.add(newUser);
        return true;
    }

    public boolean loginUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // 登录成功
            }
        }
        return false; // 登录失败
    }
}
