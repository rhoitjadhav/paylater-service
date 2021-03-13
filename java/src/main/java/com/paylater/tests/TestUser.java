package com.paylater.tests;

import com.paylater.repository.LocalDatabase;
import com.paylater.usecases.MerchantUseCase;
import com.paylater.usecases.UserUseCase;
import com.paylater.utils.ResponseValue;
import junit.framework.TestCase;

public class TestUser extends TestCase {
    private final UserUseCase userUseCase = new UserUseCase(LocalDatabase.getInstance());
    private final MerchantUseCase merchantUseCase = new MerchantUseCase(LocalDatabase.getInstance());

    @Override
    public void setUp() {
        merchantUseCase.newMerchant("m1", "m1@email.com", "0.5%");
        merchantUseCase.newMerchant("m2", "m2@email.com", "1.5%");
        merchantUseCase.newMerchant("m3", "m3@email.com", "1.25%");
    }

    // New User
    public void test1() {
        System.out.println("Running New User");

        ResponseValue result1 = userUseCase.newUser("user1", "user1@email.com", 300);
        assertTrue(result1.isSuccess());

        ResponseValue result2 = userUseCase.newUser("user1", "user1@email.com", 400);
        assertFalse(result2.isSuccess());

        ResponseValue result3 = userUseCase.newUser("user2", "user2@email.com", 400);
        assertTrue(result3.isSuccess());

        ResponseValue result4 = userUseCase.newUser("user3", "user3@email.com", 500);
        assertTrue(result4.isSuccess());
    }

    // Transaction
    public void test2() {
        System.out.println("Running Transaction");

        ResponseValue result1 = userUseCase.transaction("user2", "m1", "500");
        assertFalse(result1.isSuccess());

        ResponseValue result2 = userUseCase.transaction("user1", "m2", "300");
        assertTrue(result2.isSuccess());
    }

    // Payback Dues
    public void test3() {
        System.out.println("Running Payback Dues");

        ResponseValue result1 = userUseCase.paybackDues("user1", "200");
        assertTrue(result1.isSuccess());

        ResponseValue result2 = userUseCase.paybackDues("user1", "200");
        assertFalse(result1.isSuccess());
    }

    // Report Total Dues
    public void test4() {
        System.out.println("Running Report Total Dues");

        ResponseValue result = userUseCase.reportTotalDues();
        assertTrue(result.isSuccess());
    }

    // Report Users At Credit Limit
    public void test5() {
        System.out.println("Running Report Users at Credit Limit");

        ResponseValue result = userUseCase.reportUsersAtCreditLimit();
        assertTrue(result.isSuccess());
    }

    // Report Dues
    public void test6() {
        System.out.println("Running Report Dues");

        ResponseValue result1 = userUseCase.reportDues("user1");
        assertTrue(result1.isSuccess());

        ResponseValue result2 = userUseCase.reportDues("user4");
        assertFalse(result2.isSuccess());
    }
}
