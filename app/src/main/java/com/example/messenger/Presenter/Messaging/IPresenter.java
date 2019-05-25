package com.example.messenger.Presenter.Messaging;

import com.example.messenger.Model.Message;

public interface IPresenter {
    void LoadMessage(int accountA, int accountB);
    void SendMessage(Message message);
}
