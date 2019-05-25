package com.example.messenger.Model;

import java.util.Date;

public class Message {
    private Integer MessageID; //nếu ta không set giá trị thì GSON sẽ tự động set = null -> JSON sẽ bỏ qua trường này -> MessageID tự tạo bởi REST
    private int SenderID;
    private int ReceiverID;
    private String Content;
    private String SentDate;

    public Message(int senderID, int receiverID, String content, String sentDate) {
        SenderID = senderID;
        ReceiverID = receiverID;
        Content = content;
        SentDate = sentDate;
    }

    public String getSentDate() {
        return SentDate;
    }

    public int getMessageID() {
        return MessageID;
    }

    public int getSenderID() {
        return SenderID;
    }

    public int getReceiverID() {
        return ReceiverID;
    }

    public String getContent() {
        return Content;
    }

    public void setSenderID(int senderID) {
        SenderID = senderID;
    }

    public void setReceiverID(int receiverID) {
        ReceiverID = receiverID;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setSentDate(String sentDate) {
        SentDate = sentDate;
    }
}
