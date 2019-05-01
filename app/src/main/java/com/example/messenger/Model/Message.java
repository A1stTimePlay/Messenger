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

    public Message(String content){
        SenderID = "a4";
        ReceiverID = "a5";
        Content = content;
        SentDate= "2019-5-1";
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
}
