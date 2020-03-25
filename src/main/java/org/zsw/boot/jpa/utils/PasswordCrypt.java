package org.zsw.boot.jpa.utils;


import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.codec.digest.DigestUtils;

public abstract class PasswordCrypt {
    private static final String CRYPT_SALT = "s2bpasswordcrypt";

    public static String encrypt(String pwd) {
        String crypt = Crypt.crypt(pwd, CRYPT_SALT);
        return DigestUtils.md5Hex(crypt);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("9B8B0C7486136955013BD51FB0248C84"));
    }
}
