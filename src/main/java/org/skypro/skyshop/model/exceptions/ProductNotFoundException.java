package org.skypro.skyshop.model.exceptions;

import java.io.IOException;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }

}
