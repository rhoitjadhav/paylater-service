package com.paylater.controller;

import com.paylater.repository.LocalDatabase;
import com.paylater.usecases.MerchantUseCase;
import com.paylater.utils.ResponseValue;

public class MerchantController {
    private final MerchantUseCase merchantUseCase;

    public MerchantController() {
        this.merchantUseCase = new MerchantUseCase(LocalDatabase.getInstance());
    }

    public ResponseValue newMerchant(String[] args) {
        String name = args[0];
        String email = args[1];
        String discountPercentage = args[2];

        return merchantUseCase.newMerchant(name, email, discountPercentage);
    }

    public ResponseValue updateMerchantDiscountPercentage(String[] args) {
        String merchant = args[0];
        String percentage = args[1];

        return merchantUseCase.updateMerchantDiscountPercentage(merchant, percentage);
    }

    public ResponseValue reportDiscount(String[] args) {
        String merchant = args[0];
        return merchantUseCase.reportDiscount(merchant);
    }
}
