package com.example.messenger.Model;

public class FriendListItem {
    private int FriendListID;
    private int AccountID;
    private int FriendID;

    public FriendListItem(int friendListID, int accountID, int friendID) {
        FriendListID = friendListID;
        AccountID = accountID;
        FriendID = friendID;
    }

    public int getFriendListID() {
        return FriendListID;
    }

    public void setFriendListID(int friendListID) {
        FriendListID = friendListID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public int getFriendID() {
        return FriendID;
    }

    public void setFriendID(int friendID) {
        FriendID = friendID;
    }
}
