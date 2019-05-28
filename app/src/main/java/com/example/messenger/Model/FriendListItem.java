package com.example.messenger.Model;

public class FriendListItem {
    private int FriendListID;
    private int AccountID;
    private int FriendID;
    private String FriendName;

    public FriendListItem(int accountID, int friendID, String friendName) {
        AccountID = accountID;
        FriendID = friendID;
        FriendName = friendName;
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

    public String getFriendName() {
        return FriendName;
    }

    public void setFriendName(String friendName) {
        FriendName = friendName;
    }
}
