package com.example.messenger.Presenter.Messaging;

import com.example.messenger.Model.Message;

import java.util.List;

public interface IPresenter {
    void LoadMessage();
    void SendMessage(Message message);
}
