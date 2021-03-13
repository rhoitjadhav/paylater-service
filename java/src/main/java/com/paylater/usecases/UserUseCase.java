package com.paylater.usecases;

import com.paylater.entities.*;
import com.paylater.utils.Misc;
import com.paylater.utils.ResponseValue;
import com.paylater.repository.LocalDatabase;

import java.util.Map;


public class UserUseCase {
    private final LocalDatabase db;

    public UserUseCase(LocalDatabase db) {
        this.db = db;
    }

    /**
     * Create new user
     *
     * @param name        : name of the user
     * @param email       : email id of the user
     * @param creditLimit : credit limit set to the user
     * @return ResponseValue: success-> true if user created successfully otherwise false
     */
    public ResponseValue newUser(String name, String email, int creditLimit) {
        ResponseValue responseValue;
        User user;
        try {
            user = db.getUser(name);
            if (user != null) {
                return ResponseValue.getInstance(false, "User " + name + " Already exists",
                        Misc.getEmptyArrayList());
            }

            user = new User(name, email, creditLimit);
            db.addUser(user);
            return ResponseValue.getInstance(true, user.toString(), Misc.getEmptyArrayList());

        } catch (Exception e) {
            return ResponseValue.getInstance(false, "Error while creating user: " + e.toString(),
                    Misc.getEmptyArrayList());
        }
    }

    /**
     * @param usr    : name of user
     * @param mrchnt : name of merchant
     * @param amt    : amount of transaction
     * @return Map   : success -> true if transaction successful otherwise false
     */
    public ResponseValue transaction(String usr, String mrchnt, String amt) {
        try {
            ResponseValue responseValue;
            int amount = Integer.parseInt(amt);

            // User check
            User user = db.getUser(usr);
            if (user == null) {
                return ResponseValue.getInstance(false, "User " + usr + " not exists",
                        Misc.getEmptyArrayList());
            }

            // Merchant check
            Merchant merchant = db.getMerchant(mrchnt);
            if (merchant == null) {
                return ResponseValue.getInstance(false, "Merchant " + mrchnt + " not exists",
                        Misc.getEmptyArrayList());
            }

            if (user.getCurrentLimit() < amount) {
                responseValue = ResponseValue.getInstance(false, "rejected! (reason: credit limit)",
                        Misc.getEmptyArrayList());
            } else {
                // Update Current Limit
                int currentLimit = user.getCurrentLimit() - amount;
                user.setCurrentLimit(currentLimit);
                db.updateUser(user);

                // Update Discount Received
                double discountReceived = merchant.getDiscountReceived() + (merchant.getDiscountPercentage() * 0.01 * amount);
                merchant.setDiscountReceived(discountReceived);
                db.updateMerchant(merchant);

                responseValue = ResponseValue.getInstance(true, "success!",
                        Misc.getEmptyArrayList());
            }
            return responseValue;
        } catch (Exception e) {
            return ResponseValue.getInstance(false, "Error while executing transaction: " + e.toString(),
                    Misc.getEmptyArrayList());
        }
    }

    /**
     * Payback dues of user
     *
     * @param usr : name of user
     * @param amt : amount to be paid back
     * @return ResponseValue : success-> true if payback successful otherwise false
     */
    public ResponseValue paybackDues(String usr, String amt) {
        ResponseValue responseValue;
        try {
            int amount = Integer.parseInt(amt);
            User user = db.getUser(usr);
            if (user == null) {
                responseValue = ResponseValue.getInstance(false, "User " + usr + " not exists",
                        Misc.getEmptyArrayList());
            } else {
                int currentLimit = user.getCurrentLimit();
                user.setCurrentLimit(currentLimit + amount);

                int dues = user.getCreditLimit() - user.getCurrentLimit();
                if (dues < 0) {
                    responseValue = ResponseValue.getInstance(false,
                            "User " + usr + "is trying to pay more dues", Misc.getEmptyArrayList());
                } else {
                    db.updateUser(user);
                    responseValue = ResponseValue.getInstance(true, usr + "(dues:" + dues + ")",
                            Misc.getEmptyArrayList());
                }
            }
            return responseValue;
        } catch (Exception e) {
            responseValue = ResponseValue.getInstance(false, "Error at payback dues: " + e,
                    Misc.getEmptyArrayList());
            return responseValue;
        }
    }

    /**
     * Reports total dues of users
     *
     * @return ResponseValue: success-> true if list of users with dues otherwise false for exception
     */
    public ResponseValue reportTotalDues() {
        ResponseValue responseValue;
        try {
            Map<String, User> users = db.getUsers();
            int total = 0;

            StringBuilder messageBuilder = new StringBuilder();
            for (Map.Entry<String, User> entry : users.entrySet()) {
                User user = entry.getValue();
                String name = user.getName();
                int dues = user.getCreditLimit() - user.getCurrentLimit();
                if (dues != 0) {
                    total += dues;
                    messageBuilder.append(name).append(":").append(dues).append("\n");
                }
            }
            String message = messageBuilder.toString();

            message += "total: " + total;
            responseValue = ResponseValue.getInstance(true, message, Misc.getEmptyArrayList());

            return responseValue;

        } catch (Exception e) {
            responseValue = ResponseValue.getInstance(false, "Error at report total dues: " + e,
                    Misc.getEmptyArrayList());
            return responseValue;
        }
    }

    /**
     * Report list of users who has `0` current credit limit
     *
     * @return ResponseValue: List of users with `0` current credit limit
     */
    public ResponseValue reportUsersAtCreditLimit() {
        ResponseValue responseValue;
        try {
            User user;
            Map<String, User> users = db.getUsers();
            StringBuilder stringBuilder = new StringBuilder();

            for (Map.Entry<String, User> entry : users.entrySet()) {
                user = entry.getValue();
                if (user.getCurrentLimit() == 0) {
                    stringBuilder.append(user.getName()).append("\n");
                }
            }
            String message = stringBuilder.toString();
            responseValue = ResponseValue.getInstance(true, message, Misc.getEmptyArrayList());
            return responseValue;

        } catch (Exception e) {
            responseValue = ResponseValue.getInstance(false, "Error at report users at credit limit: " + e,
                    Misc.getEmptyArrayList());
            return responseValue;
        }
    }

    /**
     * Report dues of an user
     *
     * @param usr: name of user
     * @return ResponseValue: total dues of an user
     */
    public ResponseValue reportDues(String usr) {
        ResponseValue responseValue;
        try {
            User user = db.getUser(usr);
            if (user != null) {
                int dues = user.getCreditLimit() - user.getCurrentLimit();
                responseValue = ResponseValue.getInstance(true, "dues: (" + dues + ")",
                        Misc.getEmptyArrayList());
            } else {
                responseValue = ResponseValue.getInstance(false, "User " + usr + " not exists",
                        Misc.getEmptyArrayList());
            }
            return responseValue;

        } catch (Exception e) {
            responseValue = ResponseValue.getInstance(false, "Error at report dues: " + e,
                    Misc.getEmptyArrayList());
            return responseValue;
        }
    }
}
