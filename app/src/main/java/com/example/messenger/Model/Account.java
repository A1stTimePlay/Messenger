package com.example.messenger.Model;

public class Account {
    private String AccountID;
    private String Username;
    private String Password;

    public Account(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getAccountID() {
        return AccountID;
    }

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
