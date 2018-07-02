package com.example.introductory.introductory;

import com.example.introductory.introductory.model.User;

public class Auth {
    public static User checkUser() {
        Storage storage = Storage.getStorage();
        String email = storage.getData("email");
        String password = storage.getData("password");

        if ((email == null) || (password == null)) {
            return null;
        }

        return new User(email, password);
    }

    public static boolean authUser(User user) {
        Storage storage = Storage.getStorage();
        storage.saveData("email", user.getEmail());
        storage.saveData("password", user.getPassword());
        return true;
    }
}
