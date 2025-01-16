package com.example.blushbloom_b;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users = new ArrayList<>();

    public boolean register(String username, String password, String email, String phone, String adress, String name, String surname,String role) {
        if (findUser(username) == null) {
            users.add(new User(username, password, email, phone, adress, name, surname, role ));
            return true;
        }
        return false;
    }

    public User login(String username, String password) {
        User user = findUser(username);
        if (user != null && user.validatePassword(password)) {
            return user;
        }
        return null;
    }

    private User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
}
