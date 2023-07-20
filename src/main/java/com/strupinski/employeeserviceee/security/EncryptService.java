package com.strupinski.employeeserviceee.security;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptService {

    private static final int ROUNDS = 10;

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(ROUNDS));
    }

    public static boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}