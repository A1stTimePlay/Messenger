package com.example.messenger.View.Messaging;

import com.example.messenger.Model.Message;

import java.util.List;

public interface IView {
    void sendMessage(Message message);
    void fillRecycleView(List<Message> messageList);
}
