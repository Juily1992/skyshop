package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.exceptions.ShopError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ShopError("404", "ПРОДУКТ НЕ НАЙДЕН"));
    }
}


