package com.nelhaouari.wefoxchallenge.service;

public class PaymentServiceException extends Exception{
    public static String TYPE_GENERAL_ERROR = "General error";
    public static String TYPE_GENERAL_ERROR_MSG = "unexpected error";
    public static String TYPE_NETWORK = "network";
    public static String TYPE_DATABASE = "database";
    public static String TYPE_OTHER = "other";

    private String code;

    public PaymentServiceException(String code, String message){
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
