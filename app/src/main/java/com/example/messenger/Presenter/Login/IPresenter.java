package com.example.messenger.Presenter.Login;

public interface IPresenter {
    void Authenticate(String username, String password);
    void CreateAccount(String username, String password);
}
