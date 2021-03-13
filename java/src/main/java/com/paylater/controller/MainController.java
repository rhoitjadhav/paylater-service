package com.paylater.controller;

import com.paylater.utils.Misc;
import com.paylater.utils.ResponseValue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("ALL")
public class MainController {
    UserController userController;
    MerchantController merchantController;

    public MainController() {
        this.userController = new UserController();
        this.merchantController = new MerchantController();
    }

    public ResponseValue handler(List<String> commands) {
        ResponseValue responseValue;
        String commandType = commands.get(0);
        String commandMethod = commands.get(1);
        String[] commandArgs = commands.get(2).split("\\s+", 3);

        switch (commandType) {
            case "new":
                switch (commandMethod) {
                    case "user":
                        return userController.newUser(commandArgs);
                    case "merchant":
                        return merchantController.newMerchant(commandArgs);
                    case "txn":
                        return userController.transaction(commandArgs);
                    default:
                        return ResponseValue.getInstance(false, "Invalid command method",
                                Misc.getEmptyArrayList());
                }
            case "update":
                switch (commandMethod) {
                    case "merchant":
                        return merchantController.updateMerchantDiscountPercentage(commandArgs);
                    default:
                        return ResponseValue.getInstance(false, "Invalid command method",
                                Misc.getEmptyArrayList());
                }
            case "payback":
                switch (commandMethod) {
                    case "dues":
                        return userController.paybackDues(commandArgs);
                    default:
                        return ResponseValue.getInstance(false, "Invalid command method",
                                Misc.getEmptyArrayList());
                }
            case "report":
                switch (commandMethod) {
                    case "discount":
                        return merchantController.reportDiscount(commandArgs);
                    case "dues":
                        return userController.reportDues(commandArgs);
                    case "users-at-credit-limit":
                        return userController.reportUsersAtCreditLimit();
                    case "total-dues":
                        return userController.reportTotalDues();
                    default:
                        return ResponseValue.getInstance(false, "Invalid command method",
                                Misc.getEmptyArrayList());
                }
            default:
                return ResponseValue.getInstance(false, "Invalid command type",
                        Misc.getEmptyArrayList());
        }
    }
}
