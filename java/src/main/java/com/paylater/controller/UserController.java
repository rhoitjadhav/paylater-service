package com.paylater.controller;

import com.paylater.repository.LocalDatabase;
import com.paylater.usecases.UserUseCase;
import com.paylater.utils.ResponseValue;


public class UserController {
    private final UserUseCase useCase;

    public UserController() {
        this.useCase = new UserUseCase(LocalDatabase.getInstance());
    }

    public ResponseValue newUser(String[] args) {
        String name = args[0];
        String email = args[1];
        int creditLimit = Integer.parseInt(args[2]);

        return useCase.newUser(name, email, creditLimit);
    }

    public ResponseValue transaction(String[] args) {
        String user = args[0];
        String merchant = args[1];
        String amount = args[2];

        return useCase.transaction(user, merchant, amount);
    }

    public ResponseValue paybackDues(String[] args) {
        String user = args[0];
        String amount = args[1];

        return useCase.paybackDues(user, amount);
    }

    public ResponseValue reportTotalDues() {
        return useCase.reportTotalDues();
    }

    public ResponseValue reportUsersAtCreditLimit() {
        return useCase.reportUsersAtCreditLimit();
    }

    public ResponseValue reportDues(String[] args) {
        String user = args[0];
        return useCase.reportDues(user);
    }
}
