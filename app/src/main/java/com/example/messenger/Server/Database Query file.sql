create database MessagingApp;
use MessagingApp;

create table account(
	AccountID int auto_increment primary key,
    Username varchar(30),
    Password varchar(20)
);


create table message(
	MessageID int auto_increment primary key,
	SenderID varchar(10),
    ReceiverID varchar(10),
    Content text,
    SentDate DateTime,
    constraint FK_sender foreign key (SenderID) references account(AccountID) on delete cascade,
    constraint FK_receiver foreign key (ReceiverID) references account(AccountID) on delete cascade
);

alter table message message auto_increment = 100;

create table friendlist(
	FriendListID int auto_increment primary key,
    AccountID varchar(10),
    FriendID varchar(10),
    FriendUsername varchar(30),
    constraint FK_friendlist foreign key(AccountID) references account(AccountID)
);

-- Insert dữ liệu mẫu 
-- Bảng Account
insert into account(Username, Password)
values ("a1", "abc", "123");
insert into account(AccountID, Username, Password)
values ("a2", "def", "123");
insert into account(AccountID, Username, Password)
values ("a3", "ghi", "123");
insert into account(AccountID, Username, Password)
values ("a4", "jkl", "123");
insert into account(AccountID, Username, Password)
values ("a5", "mno", "123");
-- Bảng Message
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("a1", "a2", "Hello", "2019-4-27");
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("a2", "a1", "Hi", "2019-4-27");
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("a1", "a2", "How are you", "2019-4-27");
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("a2", "a1", "I am find, and you?", "2019-4-27");
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("a4", "a5", "Hello world", "2019-4-27");

drop database messagingapp

    