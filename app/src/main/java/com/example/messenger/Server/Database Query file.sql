create database MessagingApp
use MessagingApp

create table account(
	AccountID varchar(10) primary key,
    Username varchar(30),
    Password varchar(20)
)

create table message(
	MessageID varchar(10) primary key,
	SenderID varchar(10),
    ReceiverID varchar(10),
    AccountID varchar(10) ,
    Content text,
    constraint FK_account foreign key (AccountID) references account(AccountID)  
)

create table friendlist(
	FriendListID varchar(10) primary key,
    AccountID varchar(10),
    FriendID varchar(10),
    FriendUsername varchar(30),
    constraint FK_friendlist foreign key(AccountID) references account(AccountID)
)

abc
    
    