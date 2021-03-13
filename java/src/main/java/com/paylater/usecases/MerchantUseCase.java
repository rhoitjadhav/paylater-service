package com.paylater.usecases;

import com.paylater.entities.Merchant;
import com.paylater.repository.LocalDatabase;
import com.paylater.utils.Misc;
import com.paylater.utils.ResponseValue;

import java.util.HashMap;
import java.util.Map;

public class MerchantUseCase {
    private final LocalDatabase db;

    public MerchantUseCase(LocalDatabase db) {
        this.db = db;
    }

    /**
     * Create new merchant
     *
     * @param name               : name of merchant
     * @param email              : email of merchant
     * @param discountPercentage : discount percentage given by merchant
     * @return ResponseValue: value Merchant object
     */
    public ResponseValue newMerchant(String name, String email, String discountPercentage) {
        Merchant merchant;
        try {
            merchant = db.getMerchant(name);
            if (merchant != null) {
                return ResponseValue.getInstance(false, "Merchant " + name + " Already exists",
                        Misc.getEmptyArrayList());
            }

            merchant = new Merchant(name, email, discountPercentage);
            db.addMerchant(merchant);
            return ResponseValue.getInstance(true, merchant.toString(), Misc.getEmptyArrayList());

        } catch (Exception e) {
            return ResponseValue.getInstance(false, "Error while creating merchant: " + e.toString(),
                    Misc.getEmptyArrayList());
        }
    }

    /**
     * Update merchant discount percentage
     *
     * @param mrchnt  : name of merchant
     * @param prcntge : discount percentage given by merchant
     * @return ResponseValue: success-> true if merchant percent is updated otherwise false
     */
    public ResponseValue updateMerchantDiscountPercentage(String mrchnt, String prcntge) {
        ResponseValue responseValue;
        try {
            double percentage = Double.parseDouble(prcntge);

            Merchant merchant = db.getMerchant(mrchnt);
            if (merchant != null) {
                merchant.setDiscountPercentage(percentage);
                responseValue = ResponseValue.getInstance(true, "success!", Misc.getEmptyArrayList());

            } else {
                responseValue = ResponseValue.getInstance(false, "Merchant " + mrchnt + " not exists" ,
                        Misc.getEmptyArrayList());
            }
            return responseValue;

        } catch (Exception e) {
            return ResponseValue.getInstance(false,
                    "Error while updating merchant discount percentage: " + e.toString(),
                    Misc.getEmptyArrayList());
        }
    }

    /**
     * Report total discount received from merchant
     *
     * @param mrchnt : name of merchant
     * @return ResponseValue: total discount
     */
    public ResponseValue reportDiscount(String mrchnt) {
        ResponseValue responseValue;
        try {
            Merchant merchant = db.getMerchant(mrchnt);
            if (merchant != null) {
                String discountReceived = String.valueOf(merchant.getDiscountReceived());
                responseValue = ResponseValue.getInstance(true, discountReceived, Misc.getEmptyArrayList());
            } else {
                responseValue = ResponseValue.getInstance(false, "Merchant " + mrchnt + " not exists",
                        Misc.getEmptyArrayList());
            }
            return responseValue;

        } catch (Exception e) {
            return ResponseValue.getInstance(false,
                    "Error while getting report discount: " + e.toString(), Misc.getEmptyArrayList());
        }
    }
}