package ua.epam.radchenko.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Utility class for managing passwords.
 */
public class PasswordManager {
    /**
     * Provides a hash for a given String.
     *
     * @param password a password String
     * @return a hashed String
     *
     * @see DigestUtils
     */
    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    /**
     * Checks whether a given password match those of the user.
     *
     * @param passwordToCheck a password represented as an array of chars
     * @param passwordHash User hash password
     * @return true if password  and password hash matches
     */
    public static boolean checkSecurePassword(String passwordToCheck,
                                              String passwordHash) {
        String checkPassHash = hashPassword(passwordToCheck);
        return passwordHash.equals(checkPassHash);
    }
}
