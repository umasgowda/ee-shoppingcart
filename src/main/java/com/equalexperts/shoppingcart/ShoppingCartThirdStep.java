package com.equalexperts.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartThirdStep {

    private final List<Product> shoppingCartProducts = new ArrayList<>();
    public static final String ALPHABET_ONLY_REGEX = "[a-zA-Z]+";
    private static final BigDecimal APPLICABLE_TAX_RATE = new BigDecimal("0.125"); //0.125 is equal to 12.5%

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

    public String getShoppingCartTotalSalesTax() {
        return applyRoundRule(getTotalSalesTax());
    }

    public String getShoppingCartTotalPrice() {
        BigDecimal totalPrice = shoppingCartProducts.stream().map(product -> BigDecimal.valueOf(product.getQuantity()).multiply(product.getUnitPrice())).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPriceWithSalesTax = totalPrice.add(getTotalSalesTax());
        return applyRoundRule(totalPriceWithSalesTax);
    }

    private BigDecimal getTotalSalesTax() {
        return shoppingCartProducts.stream()
                .map(product -> BigDecimal.valueOf(product.getQuantity()).multiply(product.getUnitPrice()).multiply(APPLICABLE_TAX_RATE))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Product buildProduct(String productName, String unitPrice, int quantity) {
        if (unitPrice.isBlank() || unitPrice.matches(ALPHABET_ONLY_REGEX)) {
            throw new ShoppingCartException("Unit Price should be in valid number format");
        }
        return Product.builder().productName(productName).unitPrice(new BigDecimal(unitPrice)).quantity(quantity).build();
    }

    private String applyRoundRule(BigDecimal input) {
        BigDecimal result = input.setScale(2, RoundingMode.HALF_UP);
        return result.toString();
    }

}
