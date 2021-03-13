package com.paylater.tests;

import com.paylater.repository.LocalDatabase;
import com.paylater.usecases.MerchantUseCase;
import com.paylater.utils.ResponseValue;
import junit.framework.TestCase;

public class TestMerchant extends TestCase {
    private final MerchantUseCase merchantUseCase = new MerchantUseCase(LocalDatabase.getInstance());

    // New Merchant
    public void test1() {
        System.out.println("Running New Merchant");

        ResponseValue result1 = merchantUseCase.newMerchant("m11", "m11@email.com", "1.5%");
        assertTrue(result1.isSuccess());

        ResponseValue result2 = merchantUseCase.newMerchant("m11", "m22@email.com", "1%");
        assertFalse(result1.isSuccess());
    }

    // Update Merchant Discount Percentage
    public void test2() {
        System.out.println("Running Update Merchant Discount Percentage");

        ResponseValue result1 = merchantUseCase.updateMerchantDiscountPercentage("m11", "1");
        assertTrue(result1.isSuccess());

        ResponseValue result2 = merchantUseCase.updateMerchantDiscountPercentage("m222", "1.5%");
        assertFalse(result1.isSuccess());
    }

    // Report Discount
    public void test3() {
        System.out.println("Running Report Discount");

        ResponseValue result1 = merchantUseCase.reportDiscount("m11");
        assertTrue(result1.isSuccess());

        ResponseValue result2 = merchantUseCase.reportDiscount("m22");
        assertFalse(result1.isSuccess());
    }
}
