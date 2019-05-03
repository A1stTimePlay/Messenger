package com.example.messenger.Model;

import java.util.Date;

public class Message {
    private Integer MessageID; //nếu ta không set giá trị thì GSON sẽ tự động set = null -> JSON sẽ bỏ qua trường này -> MessageID tự tạo bởi REST
    private String SenderID;
    private String ReceiverID;
    private String Content;
    private String SentDate;

    public Message(String senderID, String receiverID, String content, String sentDate) {
        SenderID = senderID;
        ReceiverID = receiverID;
        Content = content;
        SentDate = sentDate;
    }

    public Message(String content, String sentDate){
        SenderID = "a4";
        ReceiverID = "a5";
        Content = content;
        SentDate= sentDate;
    }

    public String getSentDate() {
        return SentDate;
    }

    public int getMessageID() {
        return MessageID;
    }

    public String getSenderID() {
        return SenderID;
    }

    public String getReceiverID() {
        return ReceiverID;
    }

    public String getContent() {
        return Content;
    }

    public void setSenderID(String senderID) {
        SenderID = senderID;
    }

    public void setReceiverID(String receiverID) {
        ReceiverID = receiverID;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setSentDate(String sentDate) {
        SentDate = sentDate;
    }
}
