package by.voluevich.service;

import by.voluevich.entity.User;

public class EditPassword {

    public boolean editPassword(User user, String oldPass, String newPass) {
        if (user.getPassword().equals(oldPass)) {
            user.setPassword(newPass);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNewPass(User user, String pass) {
        return !user.getPassword().equals(pass);
    }


}

