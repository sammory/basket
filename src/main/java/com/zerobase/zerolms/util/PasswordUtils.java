package com.zerobase.zerolms.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtils {

    public static boolean equals(String plaintext, String hashed) {

        // 탈퇴 비밀번호 입력 확인 비교
        if (plaintext == null || plaintext.length() < 1) {
            return false;
        }
        if (hashed == null || hashed.length() < 1){
            return false;
        }
        return BCrypt.checkpw(plaintext, hashed);
    }

    public static String encPassword(String plaintext) {
        if (plaintext == null || plaintext.length() < 1) {
            return "";
        }
        return BCrypt.hashpw(plaintext,BCrypt.gensalt());
    }
}
