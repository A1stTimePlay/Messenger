create database MessagingApp;
use MessagingApp;

create table account(
	AccountID int auto_increment primary key,
    Username varchar(30) unique,
    Password varchar(20)
);

create table message(
	MessageID int auto_increment primary key,
	SenderID int,
    ReceiverID int,
    Content text,
    SentDate DateTime,
    constraint FK_sender foreign key (SenderID) references account(AccountID) on delete cascade, -- xóa account thì tự động xóa message
    constraint FK_receiver foreign key (ReceiverID) references account(AccountID) on delete cascade
);

alter table message auto_increment = 100; -- AccountID bắt đầu từ 0, MessageID bắt đầu từ 100 (known bug: có nhiều hơn 100 tài khoản thì sao?)

create table friendlist(
	FriendListID int auto_increment primary key,
    AccountID int,
    FriendID int,
    FriendName varchar(30),
    constraint FK_account foreign key(AccountID) references account(AccountID),
    constraint FK_friend foreign key(FriendID) references account(AccountID),
    constraint FK_friend_name foreign key(FriendName) references account (Username)
);

-- Insert dữ liệu mẫu 
-- Bảng Account
insert into account(Username, Password)
values ("abc", "123");
insert into account(Username, Password)
values ("def", "123");
insert into account(Username, Password)
values ("ghi", "123");
insert into account(Username, Password)
values ("jkl", "123");
insert into account(Username, Password)
values ("mno", "123");
-- Bảng Message
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("1", "2", "Hello", "2019-4-1");
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("2", "1", "Hi", "2019-4-2");
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("1", "2", "How are you", "2019-4-3");
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("2", "1", "I am find, and you?", "2019-4-4");
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("4", "5", "Hello world", "2019-4-5");
insert into message(SenderID, ReceiverID, Content, SentDate)
values ("1", "3", "Hello world", "2019-4-6");

-- Bảng FriendList
insert into friendlist(AccountID, FriendID, FriendName)
values(1, 2, "def"); 
insert into friendlist(AccountID, FriendID, FriendName)
values(1, 3, "ghi"); 
insert into friendlist(AccountID, FriendID, FriendName)
values(1, 4, "jkl"); 
insert into friendlist(AccountID, FriendID, FriendName)
values(1, 5, "mno"); 

ALTER USER 'root'@'localhost' IDENTIFIED BY '';



    