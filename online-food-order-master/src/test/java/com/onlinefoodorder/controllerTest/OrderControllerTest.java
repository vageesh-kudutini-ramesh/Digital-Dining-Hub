package com.onlinefoodorder.controllerTest;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.controller.OrderController;
import com.onlinefoodorder.dao.CartDao;
import com.onlinefoodorder.dao.OrderDao;
import com.onlinefoodorder.model.Cart;
import com.onlinefoodorder.model.Orders;
import com.onlinefoodorder.model.User;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private CartDao cartDao;

    @Mock
    private OrderDao orderDao;
    
    @Autowired
    private MockMvc mockMvc; 
    
    @InjectMocks
    private OrderController orderController;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void orderfoods() {
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setId(1);

        when(session.getAttribute("active-user")).thenReturn(user);
        when(cartDao.findByUserId(user.getId())).thenReturn(Collections.singletonList(new Cart()));

        ModelAndView result = orderController.orderfoods(session);

        verify(orderDao, times(1)).save(any());
        verify(cartDao, times(1)).delete(any());

        assertEquals("index", result.getViewName());

        // Use this assertion with a regular expression
        assertTrue(result.getModel().get("status").toString().matches("Order placed Successfully, Order Id is [A-Za-z0-9]+"));

    }

    @Test
    void goToMyOrder() {
        // Arrange
        User user = new User();
        user.setId(1);
        HttpSession session = new MockHttpSession();
        session.setAttribute("active-user", user);

        Orders order = new Orders();
        // Set attributes for the order object as needed

        List<Orders> ordersList = Collections.singletonList(order);

        when(orderDao.findByUserId(user.getId())).thenReturn(ordersList);

        // Act
        ModelAndView result = orderController.goToMyOrder(session);

        // Assert
        verify(orderDao, times(1)).findByUserId(user.getId());

        List<Orders> actualOrders = (List<Orders>) result.getModel().get("orders");
        assertNotNull(actualOrders);
        assertEquals(ordersList.size(), actualOrders.size());

        // Compare relevant attributes manually
        for (int i = 0; i < ordersList.size(); i++) {
            Orders expectedOrder = ordersList.get(i);
            Orders actualOrder = actualOrders.get(i);

            // Compare relevant attributes, adjust as needed
            assertEquals(expectedOrder.getOrderDate(), actualOrder.getOrderDate());
            assertEquals(expectedOrder.getOrderId(), actualOrder.getOrderId());
            // Add more attribute comparisons as needed
        }

        assertEquals("myorder", result.getViewName());
    }

    @Test
    void searchByOrderId() {
        ModelAndView result = orderController.searchByOrderId("123");

        assertEquals("myorder", result.getViewName());
        assertEquals(Collections.emptyList(), result.getModel().get("orders"));
    }
  
    @Test
    void testSearchByOrderDate() throws Exception {
        // Mock data
        User user = new User();
        user.setId(1);

        List<Orders> ordersList = new ArrayList<>(); // Assuming Orders is a class in your application
        // Add some Orders to the list...

        // Create a MockHttpSession
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("active-user", user);

        // Mocking behavior
        when(orderDao.findByOrderDateAndUserId(anyString(), eq(user.getId()))).thenReturn(ordersList);

        // Perform the MVC request and verify
        mockMvc.perform(get("/searchorderbydate")
                .param("orderdate", "2023-12-11") // Adjust the date based on your needs
                .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attribute("orders", ordersList));

        // Verify that the expected methods were called
        verify(orderDao, times(1)).findByOrderDateAndUserId(anyString(), eq(user.getId()));
      
    }
    
    @Test
    void checkout() {
        ModelAndView result = orderController.searchByOrderDate("100");

        assertEquals("checkout", result.getViewName());
        assertEquals("100", result.getModel().get("amount"));
    }

    @Test
    void addDeliveryStatus() {
        // Arrange
        when(orderDao.findByOrderId("123")).thenReturn(Collections.singletonList(new Orders()));

        // Act
        ModelAndView result = orderController.addDeliveryStatus("123", "DELIVERED", "2023-01-02");

        // Assert
        verify(orderDao, times(1)).findByOrderId("123");
        verify(orderDao, times(1)).save(any()); // Use any() to match any argument

        assertEquals("index", result.getViewName());
        assertEquals("Order Delivery Status Updated.", result.getModel().get("status"));
    }

}

