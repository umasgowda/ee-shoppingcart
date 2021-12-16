package com.equalexperts.shoppingcart;

public class ShoppingCartException extends RuntimeException {

    private String message;

    public ShoppingCartException(String message) {
        this.message = message;
    }

}
