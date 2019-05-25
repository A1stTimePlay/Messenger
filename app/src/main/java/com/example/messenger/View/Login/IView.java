package com.example.messenger.View.Login;

import com.example.messenger.Model.Account;

public interface IView {
    void successful(Account account);
    void decline();
}
