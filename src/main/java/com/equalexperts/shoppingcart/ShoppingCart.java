package com.equalexperts.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Product> shoppingCartProducts = new ArrayList<>();
    private BigDecimal totalPriceWithSalesTax = BigDecimal.ZERO;
    private BigDecimal totalSalesTax = BigDecimal.ZERO;
    private static final BigDecimal APPLICABLE_TAX_RATE = new BigDecimal("0.125"); //0.125 is equal to 12.5%

    public List<Product> showShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void addToShoppingCart(String productName, BigDecimal unitPrice, int quantity) {
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
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(Product product: shoppingCartProducts) {
            BigDecimal itemPriceWithQuantity = BigDecimal.valueOf(product.getQuantity()).multiply(product.getUnitPrice());
            totalPrice = totalPrice.add(itemPriceWithQuantity);
            this.totalPriceWithSalesTax = totalPrice.add(totalPrice.multiply(APPLICABLE_TAX_RATE));
            this.totalSalesTax = this.totalSalesTax.add(itemPriceWithQuantity.multiply(APPLICABLE_TAX_RATE));
        }
        return applyRoundRule(totalPriceWithSalesTax);
    }

    private BigDecimal getTotalSalesTax() {
        return this.totalSalesTax;
    }

    private Product buildProduct(String productName, BigDecimal unitPrice, int quantity) {
        return Product.builder().productName(productName).unitPrice(unitPrice).quantity(quantity).build();
    }

    private String applyRoundRule(BigDecimal input) {
        BigDecimal result = input.setScale(2, RoundingMode.HALF_UP);
        return result.toString();
    }

}
