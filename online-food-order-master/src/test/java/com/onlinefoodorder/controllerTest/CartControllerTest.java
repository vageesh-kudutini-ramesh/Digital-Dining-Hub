package com.onlinefoodorder.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.controller.CartController;
import com.onlinefoodorder.dao.CartDao;
import com.onlinefoodorder.model.Cart;

class CartControllerTest {

    @Mock
    private CartDao cartDao;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddToCart() {
        // Arrange
        Cart cart = new Cart();
        ModelAndView expectedModelAndView = new ModelAndView();
        expectedModelAndView.addObject("status", "Foods added to cart!");
        expectedModelAndView.setViewName("index");

        // Mock behavior
        when(cartDao.save(any(Cart.class))).thenReturn(cart); // Assuming save() returns the saved entity

        // Act
        ModelAndView result = cartController.addtoCart(cart);

        // Assert
        assertEquals(expectedModelAndView.getViewName(), result.getViewName());
        assertEquals(expectedModelAndView.getModelMap().get("status"), result.getModelMap().get("status"));
        verify(cartDao, times(1)).save(any(Cart.class));
    }


    @Test
    void testDeleteProductFromCart() {
        // Arrange
        int cartId = 1;
        Cart cart = new Cart();
        Optional<Cart> optionalCart = Optional.of(cart);
        ModelAndView expectedModelAndView = new ModelAndView();
        expectedModelAndView.addObject("status", "Selected Food removed from Cart!");
        expectedModelAndView.setViewName("index");

        // Mock behavior
        when(cartDao.findById(cartId)).thenReturn(optionalCart);
        doNothing().when(cartDao).delete(any(Cart.class));

        // Act
        ModelAndView result = cartController.deleteProductFromCart(cartId);

        // Assert
        assertEquals(expectedModelAndView.getViewName(), result.getViewName());
        assertEquals(expectedModelAndView.getModel().get("status"), result.getModel().get("status"));
        verify(cartDao, times(1)).delete(any(Cart.class));
    }
}
