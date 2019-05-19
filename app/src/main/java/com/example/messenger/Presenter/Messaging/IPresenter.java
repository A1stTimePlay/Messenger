package com.example.messenger.Presenter.Messaging;

import com.example.messenger.Model.Message;

public interface IPresenter {
    void LoadMessage();
    void SendMessage(Message message);
}
