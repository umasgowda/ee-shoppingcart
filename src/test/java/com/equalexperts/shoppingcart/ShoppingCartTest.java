package com.equalexperts.shoppingcart;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest {

    private ShoppingCart shoppingCart = new ShoppingCart();

    @Test
    public void givenAnEmptyShoppingCart_whenProductsAddedWithZeroQuantity_thenCheckTotalPriceAndSalesTax() {
        //Given
        assertThat(shoppingCart.showShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", new BigDecimal("39.99"), 0);

        // Then
        assertThat(shoppingCart.showShoppingCartProducts().size()).isEqualTo(0);
        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("0.00");
        assertThat(shoppingCart.getShoppingCartTotalSalesTax()).isEqualTo("0.00");
    }

    @Test
    public void givenAnEmptyShoppingCart_whenProductsAddedWithNegativeQuantity_thenCheckTotalPriceAndSalesTax() {
        //Given
        assertThat(shoppingCart.showShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", new BigDecimal("39.99"), -1);

        // Then
        assertThat(shoppingCart.showShoppingCartProducts().size()).isEqualTo(0);
        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("0.00");
        assertThat(shoppingCart.getShoppingCartTotalSalesTax()).isEqualTo("0.00");
    }

    @Test
    public void givenAnEmptyShoppingCart_whenFiveSoapsAdded_thenCheckTotalPriceAndTotalSalesTax() {
        //Given
        assertThat(shoppingCart.showShoppingCartProducts().size()).isEqualTo(0);

        // When
        shoppingCart.addToShoppingCart("Dove Soap", new BigDecimal("39.99"), 5);

        // Then
        Product dove_soap = Product.builder().productName("Dove Soap").unitPrice(new BigDecimal("39.99")).quantity(5).build();
        assertThat(shoppingCart.showShoppingCartProducts()).containsAll(List.of(dove_soap));

        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("224.94");
        assertThat(shoppingCart.getShoppingCartTotalSalesTax()).isEqualTo("24.99");
    }

    @Test
    public void givenAnEmptyShoppingCart_whenMultipleProductsAdded_thenCheckTotalPriceAndTotalSalesTax() {
        //Given
        assertThat(shoppingCart.showShoppingCartProducts().size()).isEqualTo(0);
        BigDecimal doveSoapPrice  = new BigDecimal("39.99");
        BigDecimal axeDeoPrice = new BigDecimal("99.99");

        // When
        shoppingCart.addToShoppingCart("Dove Soap", doveSoapPrice, 2);
        shoppingCart.addToShoppingCart("Axe Deos",  axeDeoPrice, 2);

        //Then
        Product dove_soap = Product.builder().productName("Dove Soap").unitPrice(doveSoapPrice).quantity(2).build();
        Product axe_deos = Product.builder().productName("Axe Deos").unitPrice(axeDeoPrice).quantity(2).build();
        assertThat(shoppingCart.showShoppingCartProducts()).containsAll(Arrays.asList(dove_soap, axe_deos));

        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("314.96");
        assertThat(shoppingCart.getShoppingCartTotalSalesTax()).isEqualTo("35.00");
    }


}