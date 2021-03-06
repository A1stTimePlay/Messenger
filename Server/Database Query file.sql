create database MessagingApp;
use MessagingApp;

create table account(
	AccountID int auto_increment,
    Username varchar(30) ,
    Password varchar(20),
    primary key(AccountID, Username)
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
    constraint FK_friend foreign key(FriendID, FriendName) references account(AccountID, Username),
    constraint CS_unique unique (AccountID, FriendID)
);

-- Insert dữ liệu mẫu 
-- Bảng Account
insert into account(Username, Password)
values ("Nguyen Xuan An", "123");
insert into account(Username, Password)
values ("Tran The Khoi", "123");
insert into account(Username, Password)
values ("Rosa Shea", "123");
insert into account(Username, Password)
values ("Barbara Gillespie", "123");
insert into account(Username, Password)
values ("Levi Beard", "123");
insert into account(Username, Password)
values ("Scarlet Lutz", "123");
insert into account(Username, Password)
values ("Darryl Randall", "123");
insert into account(Username, Password)
values ("Matilda Vega", "123");
insert into account(Username, Password)
values ("Dixie Valencia", "123");
insert into account(Username, Password)
values ("Ayden Ray", "123");

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
values(1, 2, "Tran The Khoi"); 
insert into friendlist(AccountID, FriendID, FriendName)
values(1, 3, "Rosa Shea"); 
insert into friendlist(AccountID, FriendID, FriendName)
values(1, 4, "Barbara Gillespie"); 
insert into friendlist(AccountID, FriendID, FriendName)
values(1, 5, "Levi Beard"); 

insert into friendlist(AccountID, FriendID, FriendName)
values(2, 1, "Nguyen Xuan An"); 

ALTER USER 'root'@'localhost' IDENTIFIED BY '';