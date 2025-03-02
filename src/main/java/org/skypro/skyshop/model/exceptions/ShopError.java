package org.skypro.skyshop.model.exceptions;

public class ShopError {
    private String message;
    private String code;

    public ShopError(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ShopError {message= " + message + ", code= " + code + "}";
    }
}
