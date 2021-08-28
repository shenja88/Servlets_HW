package by.voluevich.service;

import by.voluevich.entity.User;

public class EditName {

    public boolean editName(User user, String newName) {
        if (checkNewName(user, newName)) {
            user.setName(newName);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkNewName(User user, String name) {
        return !user.getName().equals(name);
    }
}
