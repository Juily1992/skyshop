package org.skypro.skyshop.model.exceptions;


public class NoSuchProductEsception extends RuntimeException {
    public NoSuchProductEsception(String message) {
        super(message);
    }
}
