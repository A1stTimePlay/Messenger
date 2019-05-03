package com.example.messenger.Model;

public class Account {
    private Integer AccountID;
    private String Username;
    private String Password;

    public Account(String username, String password) {
        Username = username;
        Password = password;
    }

    public int getAccountID() {
        return AccountID;
    }

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
