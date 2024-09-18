package com.onlinefoodorder.controllerTest;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.controller.UserController;
import com.onlinefoodorder.dao.UserDao;
import com.onlinefoodorder.model.User;

class UserControllerTest {

    private UserDao userDao;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userDao = mock(UserDao.class);
        userController = new UserController();
        ReflectionTestUtils.setField(userController, "userDao", userDao);
        MockitoAnnotations.openMocks(this);
       
    }
    @Test
    void testGoToLoginPage() {
        // Act
        String result = userController.goToLoginPage();

        // Assert
        assertEquals("userlogin", result);
    }

    @Test
    void testGoToRegisterPage() {
        // Act
        String result = userController.goToRegisterPage();

        // Assert
        assertEquals("userregister", result);
    }

    @Test
    void testLogout() {
        // Arrange
        HttpSession session = mock(HttpSession.class);

        // Act
        ModelAndView result = userController.logout(session);

        // Assert
        assertEquals("index", result.getViewName());
        assertEquals("Logged out Successfully", result.getModel().get("status"));
    }

    @Test
    void testRegisterAdmin_Success() {
        // Arrange
        User user = new User();
        user.setFirstname("John");
        when(userDao.save(any(User.class))).thenReturn(user);

        // Act
        ModelAndView result = userController.registerAdmin(user);

        // Assert
        assertEquals("userlogin", result.getViewName());
        assertEquals("John Successfully Registered!", result.getModel().get("status"));
    }
    
    @Test
    void testRegisterAdmin_Failure() {
        // Arrange
        User user = new User();
        user.setEmailid("test@example.com");
        user.setPassword("password");
        user.setFirstname("John");

        // Mock UserDao methods
        when(userDao.findByEmailid(anyString())).thenReturn(user); // Return a user to simulate an existing user
        when(userDao.save(any(User.class))).thenReturn(null); // Return null to simulate a failed save

        // Act
        ModelAndView result = userController.registerAdmin(user);

        // Assert
        assertNotNull(result); // Ensure the result is not null

        // If registration is expected to fail, assert the redirected view and status
        if ("userregister".equals(result.getViewName())) {
            assertNotNull(result.getModel().get("status"));
            assertEquals("John Failed to Registered User!", result.getModel().get("status"));
        } else {
            fail("Registration was expected to fail, but the result is not as expected.");
        }
    }

    @Test
    void testForgetPassword_Success() {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        // Mocking the case when no user is found
        when(userDao.findByEmailidAndMobileno(any(String.class), any(String.class))).thenReturn(null);

        // Act
        ModelAndView result = userController.forgetpassword(new User());

        // Assert
        assertEquals("userregister", result.getViewName());
        assertEquals("No User found!", result.getModel().get("status"));
    }


    @Test
    void testForgetPassword_Failure() {
        // Arrange
        when(userDao.findByEmailidAndMobileno(any(String.class), any(String.class))).thenReturn(null);

        // Act
        ModelAndView result = userController.forgetpassword(new User());

        // Assert
        assertEquals("userregister", result.getViewName());
        assertEquals("No User found!", result.getModel().get("status"));
    }
    
    @Test
    void testLoginAdmin_Success() {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        User user = new User();
        user.setFirstname("John");  // Set a non-null firstname
        when(userDao.findByEmailidAndPassword(any(String.class), any(String.class))).thenReturn(user);

        // Act
        ModelAndView result = userController.loginAdmin(request, "email", "password");

        // Assert
        assertEquals("index", result.getViewName());
        assertEquals("John Successfully Logged In!", result.getModel().get("status"));
    }

    
    @Test
    void testLoginAdmin_Success_NullFirstname() {
        // Arrange
        User user = new User();
        user.setEmailid("test@example.com");
        user.setPassword("password");
        user.setFirstname(null);  // Simulating null firstname

        // Mock HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);

        // Mock HttpSession
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        // Mock UserDao methods
        when(userDao.findByEmailidAndPassword(anyString(), anyString())).thenReturn(user);

        // Act
        ModelAndView result = userController.loginAdmin(request, "test@example.com", "password");

        // Assert
        assertNotNull(result); // Ensure the result is not null

        // If login is successful, assert the redirected view and status
        if (result != null && "index".equals(result.getViewName())) {
            assertNotNull(result.getModel().get("status"));
            // Check if firstname is null and handle it appropriately in the assertion
            if (user.getFirstname() != null) {
                assertEquals(user.getFirstname() + " Successfully Logged In!", result.getModel().get("status"));
            } else {
                assertEquals("Successfully Logged In!", result.getModel().get("status"));
            }
        } else {
            fail("Login was expected to be successful, but the result is not as expected.");
        }
    }


   

    @Test
    void testLoginAdmin_Failure() {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        when(userDao.findByEmailidAndPassword(any(String.class), any(String.class))).thenReturn(null);

        // Act
        ModelAndView result = userController.loginAdmin(request, "email", "password");

        // Assert
        assertEquals("index", result.getViewName());
        assertEquals("Failed to login!", result.getModel().get("status"));
    }

}

