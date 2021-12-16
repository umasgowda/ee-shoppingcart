package com.equalexperts.shoppingcart;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartSecondStepTest {
    private ShoppingCartSecondStep shoppingCart = new ShoppingCartSecondStep();

    @Test
    public void givenAnEmptyShoppingCart_whenProductsAddedWithZeroQuantity_thenCheckTotalPrice() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", "39.99", 0);

        // Then
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);
        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("0.00");
    }

    @Test
    public void givenAnEmptyShoppingCart_whenProductsAddedWithNegativeQuantity_thenCheckTotalPrice() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", "39.99", -1);

        // Then
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);
        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("0.00");
    }

    @Test
    public void givenAnEmptyShoppingCart_whenAProductIsAddedWith0UnitPrice_thenCheckTotalPrice() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", "0", 1);

        // Then
        assertThat(shoppingCart.getShoppingCartProducts()).hasSize(1);
        Product dove_soap = Product.builder().productName("Dove Soap").unitPrice(new BigDecimal("0")).quantity(1).build();
        assertThat(shoppingCart.getShoppingCartProducts()).containsAll(List.of(dove_soap));

        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("0.00");
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
    public void givenAnEmptyShoppingCart_whenFiveSoapsAdded_thenCheckTotalPrice() {
        //Given
        assertThat(shoppingCart.getShoppingCartProducts().size()).isEqualTo(0);

        //When
        shoppingCart.addToShoppingCart("Dove Soap", "39.99", 5);

        // Then
        assertThat(shoppingCart.getShoppingCartProducts()).hasSize(1);
        Product dove_soap = Product.builder().productName("Dove Soap").unitPrice(new BigDecimal("39.99")).quantity(5).build();
        assertThat(shoppingCart.getShoppingCartProducts()).containsAll(List.of(dove_soap));

        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("199.95");
    }

    @Test
    public void givenShoppingCartWithFiveSoaps_whenAnotherThreeSoapsAreAdded_thenCheckTotalPrice() {
        // Given
        shoppingCart.addToShoppingCart("Dove Soap", "39.99", 5);
        // When
        shoppingCart.addToShoppingCart("Dove Soap", "39.99", 3);
        // Then
        Product dove_soap1 = Product.builder().productName("Dove Soap").unitPrice(new BigDecimal("39.99")).quantity(5).build();
        Product dove_soap2 = Product.builder().productName("Dove Soap").unitPrice(new BigDecimal("39.99")).quantity(3).build();
        assertThat(shoppingCart.getShoppingCartProducts()).containsAll(List.of(dove_soap1, dove_soap2));
        assertThat(shoppingCart.getShoppingCartTotalPrice()).isEqualTo("319.92");
    }


}