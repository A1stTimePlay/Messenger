package com.example.messenger.Presenter.Login;

import com.example.messenger.Model.Account;

public interface IPresenter {
    void Authenticate(Account account);
    void CreateAccount(Account account);
}
