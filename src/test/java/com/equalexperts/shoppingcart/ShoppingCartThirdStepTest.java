package com.equalexperts.shoppingcart;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartThirdStepTest {

    private ShoppingCartThirdStep shoppingCart = new ShoppingCartThirdStep();

    @Test
    public void givenAnEmptyShoppingCart_whenProductsAddedWithZeroQuantity_thenCheckTotalPriceAndSalesTax() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", "39.99", 0);

        // Then
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);
        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("0.00");
        assertThat(shoppingCart.getShoppingCartTotalSalesTax()).isEqualTo("0.00");
    }

    @Test
    public void givenAnEmptyShoppingCart_whenProductsAddedWithNegativeQuantity_thenCheckTotalPriceAndSalesTax() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", "39.99", -1);

        // Then
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);
        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("0.00");
        assertThat(shoppingCart.getShoppingCartTotalSalesTax()).isEqualTo("0.00");
    }

    @Test
    public void givenAnEmptyShoppingCart_whenAProductIsAddedWith0UnitPrice_thenCheckTotalPriceAndSalesTax() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", "0", 1);

        // Then
        assertThat(shoppingCart.getShoppingCartProducts()).hasSize(1);
        Product dove_soap = Product.builder().productName("Dove Soap").unitPrice(new BigDecimal("0")).quantity(1).build();
        assertThat(shoppingCart.getShoppingCartProducts()).containsAll(List.of(dove_soap));

        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("0.00");
        assertThat(shoppingCart.getShoppingCartTotalSalesTax()).isEqualTo("0.00");
    }

    @Test(expected = ShoppingCartException.class)
    public void givenAnEmptyShoppingCart_whenAProductIsAddedWithWhiteSpaceUnitPrice_shouldThrowAnException() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", " ", 1);
    }


    @Test(expected = ShoppingCartException.class)
    public void givenAnEmptyShoppingCart_whenAProductIsAddedWithNullUnitPrice_shouldThrowAnException() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", "null", 1);
    }

    @Test(expected = ShoppingCartException.class)
    public void givenAnEmptyShoppingCart_whenAProductIsAddedWithEmptyStringUnitPrice_shouldThrowAnException() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", "", 1);
    }

    @Test
    public void givenAnEmptyShoppingCart_whenFiveSoapsAdded_thenCheckTotalPriceAndTotalSalesTax() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        // When
        shoppingCart.addToShoppingCart("Dove Soap", "39.99", 5);

        // Then
        Product dove_soap = Product.builder().productName("Dove Soap").unitPrice(new BigDecimal("39.99")).quantity(5).build();
        assertThat(shoppingCart.getShoppingCartProducts()).containsAll(List.of(dove_soap));

        assertThat(shoppingCart.getShoppingCartTotalSalesTax()).isEqualTo("24.99");
        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("224.94");
    }

    @Test
    public void givenAnEmptyShoppingCart_whenMultipleProductsAdded_thenCheckTotalPriceAndTotalSalesTax() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        // When
        shoppingCart.addToShoppingCart("Dove Soap", "39.99", 2);
        shoppingCart.addToShoppingCart("Axe Deos", "99.99", 2);

        //Then
        Product dove_soap = Product.builder().productName("Dove Soap").unitPrice(new BigDecimal("39.99")).quantity(2).build();
        Product axe_deos = Product.builder().productName("Axe Deos").unitPrice(new BigDecimal("99.99")).quantity(2).build();
        assertThat(shoppingCart.getShoppingCartProducts()).containsAll(Arrays.asList(dove_soap, axe_deos));

        assertThat(shoppingCart.getShoppingCartTotalSalesTax()).isEqualTo("35.00");
        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("314.96");
    }


}