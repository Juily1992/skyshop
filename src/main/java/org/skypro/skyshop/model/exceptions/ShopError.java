package org.skypro.skyshop.model.exceptions;

public class ShopError extends RuntimeException {
    public ShopError(String message, String string) {
        super(message);
    }
}
