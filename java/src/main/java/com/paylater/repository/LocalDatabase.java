package com.paylater.repository;

import java.util.Map;
import java.util.HashMap;

import com.paylater.entities.*;

public class LocalDatabase implements Database {
    private static LocalDatabase instance;
    private final Map<String, User> userList = new HashMap<>();
    private final Map<String, Merchant> merchantList = new HashMap<>();

    public static LocalDatabase getInstance() {
        if (instance == null) {
            instance = new LocalDatabase();
        }
        return instance;
    }

    public User getUser(String name) {
        return userList.getOrDefault(name, null);
    }

    public Map<String, User> getUsers() {
        return userList;
    }

    public Merchant getMerchant(String name) {
        return merchantList.getOrDefault(name, null);
    }

    public Map<String, Merchant> getMerchants() {
        return merchantList;
    }

    public void addUser(User user) {
        String name = user.getName();
        userList.put(name, user);
    }

    public void addMerchant(Merchant merchant) {
        String name = merchant.getName();
        merchantList.put(name, merchant);
    }

    public void updateUser(User user) {
        String name = user.getName();
        userList.put(name, user);
    }

    public void updateMerchant(Merchant merchant) {
        String name = merchant.getName();
        merchantList.put(name, merchant);
    }

}
