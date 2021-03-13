package com.paylater.repository;

import java.util.Map;

import com.paylater.entities.*;

public interface Database {
    public String getUser(String name);

    public Map<String, Map<String, String>> getUsers();

    public String getMerchant(String name);

    public Map<String, Map<String, String>> getMerchants();

    public void addUser(User user);

    public void addMerchant(Merchant user);

    public void updateUser(String name, Map<String, String> user);

    public void updateMerchant(String name, Map<String, String> merchant);
}
