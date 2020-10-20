package fr.eseo.mb.android.posterate.data.model;
/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {
    public static String token = null;
    public static String displayName;
    public  static String role;
    public  static String fullName;
    public LoggedInUser(String token, String displayName) {
        this.token = token;
        this.displayName = displayName;
    }

    public static String getDisplayName() {
        return displayName;
    }
    public static String getFullName() {
        return fullName;
    }
    public static String getRole() {
        return role;
    }
    public static String getToken() {return token;}
}