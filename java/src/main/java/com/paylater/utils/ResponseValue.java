package com.paylater.utils;

import java.util.ArrayList;

public class ResponseValue {
    private static ResponseValue instance;
    private boolean success;
    private String message;
    private ArrayList<?> data;

//    `public ResponseValue(boolean success, String message, ArrayList<?> data) {
//        this.success = success;
//        this.message = message;
//        this.data = data;
//    }`

    public static ResponseValue getInstance(boolean success, String message, ArrayList<?> data) {
        if (instance == null) {
            instance = new ResponseValue();
        }
        setSuccess(instance, success);
        setMessage(instance, message);
        setData(instance, data);
        return instance;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<?> getData() {
        return data;
    }

    public static void setSuccess(ResponseValue responseValue, boolean success) {
        responseValue.success = success;
    }

    public static void setMessage(ResponseValue responseValue, String message) {
        responseValue.message = message;
    }

    public static void setData(ResponseValue responseValue, ArrayList<?> data) {
        responseValue.data = data;
    }
}
