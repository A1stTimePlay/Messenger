package com.example.messenger.Presenter.AddFriend;

import com.example.messenger.Model.FriendListItem;

public interface IPresenter {
    void loadAccountList();
    void addFriend(FriendListItem friendListItem);
}
