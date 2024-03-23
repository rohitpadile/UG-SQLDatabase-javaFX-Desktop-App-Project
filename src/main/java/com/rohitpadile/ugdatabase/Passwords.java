package com.rohitpadile.ugdatabase;

public class Passwords {
    private String currentAdminPassword = "admin123";
    private String currentUserPassword = "user123";

    public String getCurrentAdminPassword() {
        return currentAdminPassword;
    }

    public void setCurrentAdminPassword(String currentAdminPassword) {
        this.currentAdminPassword = currentAdminPassword;
    }

    public String getCurrentUserPassword() {
        return currentUserPassword;
    }

    public void setCurrentUserPassword(String currentUserPassword) {
        this.currentUserPassword = currentUserPassword;
    }
}
