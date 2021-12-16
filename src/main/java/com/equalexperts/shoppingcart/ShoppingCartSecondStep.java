package com.equalexperts.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCartSecondStep {

    public static final String ALPHABET_ONLY_REGEX = "[a-zA-Z]+";
    private final List<Product> shoppingCartProducts = new ArrayList<>();

    public String getShoppingCartTotalPrice() {
        BigDecimal totalPrice = shoppingCartProducts.stream().map(product -> BigDecimal.valueOf(product.getQuantity()).multiply(product.getUnitPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal result = totalPrice.setScale(2, RoundingMode.HALF_UP);
        return result.toString();
    }

    public List<Product> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void addToShoppingCart(String productName, String unitPrice, int quantity) {
        List<Product> products = new ArrayList<>();
        if (quantity > 0) {
            products.add(buildProduct(productName, unitPrice, quantity));
        }
        shoppingCartProducts.addAll(products);
    }

    private Product buildProduct(String productName, String unitPrice, int quantity) {
        if (unitPrice.isBlank() || unitPrice.matches(ALPHABET_ONLY_REGEX)) {
            throw new ShoppingCartException("Unit Price should be in valid number format");
        }
        return Product.builder().productName(productName).unitPrice(new BigDecimal(unitPrice)).quantity(quantity).build();
    }

}
