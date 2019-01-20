package com.codecool.airbnbmanager.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }

}
