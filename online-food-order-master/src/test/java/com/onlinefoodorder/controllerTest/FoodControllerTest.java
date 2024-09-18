package com.onlinefoodorder.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.controller.FoodController;
import com.onlinefoodorder.dao.FoodDao;
import com.onlinefoodorder.model.Food;

import java.lang.reflect.Field;


class FoodControllerTest {

    @Mock
    private FoodDao foodDao;

    private FoodController foodController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        foodController = new FoodController();
        setField(foodController, "foodDao", foodDao);
    }
    
    private static void setField(Object targetObject, String fieldName, Object value) {
        try {
            Field field = targetObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(targetObject, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testAddFood() throws IOException, ServletException {
        // Mocking HttpServletRequest and HttpSession
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("name")).thenReturn("Test Food");
        when(request.getParameter("description")).thenReturn("Test Description");
        when(request.getParameter("price")).thenReturn("10.99");
        when(request.getParameter("discount")).thenReturn("2.00");
        when(request.getParameter("categoryId")).thenReturn("1");

        // Mocking Part (MultipartFile in Spring)
        Part part = mock(Part.class);
        when(part.getSubmittedFileName()).thenReturn("test_image.jpg");
        when(part.getInputStream()).thenReturn(mock(InputStream.class));
        when(request.getPart("image")).thenReturn(part);

        // Mocking FoodDao save method
        Food savedFood = new Food();
        savedFood.setId(1);
        when(foodDao.save(any(Food.class))).thenReturn(savedFood);

        // Test the addFood method
        ModelAndView result = foodController.addProduct(request, session);

        // Assertions
        assertEquals("index", result.getViewName());
        assertEquals("Food Added Successfully.", result.getModel().get("status"));
    }

    @Test
    void testUpdateFood() throws IOException, ServletException {
        // Mocking HttpServletRequest and HttpSession
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Updated Test Food");
        when(request.getParameter("description")).thenReturn("Updated Test Description");
        when(request.getParameter("price")).thenReturn("12.99");
        when(request.getParameter("discount")).thenReturn("3.00");
        when(request.getParameter("categoryId")).thenReturn("2");

        // Mocking Part (MultipartFile in Spring)
        Part part = mock(Part.class);
        when(part.getSubmittedFileName()).thenReturn("updated_test_image.jpg");
        when(part.getInputStream()).thenReturn(mock(InputStream.class));
        when(request.getPart("image")).thenReturn(part);

        // Mocking FoodDao findById and save methods
        Food existingFood = new Food();
        existingFood.setId(1);
        when(foodDao.findById(1)).thenReturn(Optional.of(existingFood));
        when(foodDao.save(any(Food.class))).thenReturn(existingFood);

        // Test the updateFood method
        ModelAndView result = foodController.updateProduct(request, session);

        // Assertions
        assertEquals("index", result.getViewName());
        assertEquals("Food updated Successfully.", result.getModel().get("status"));
    }

    @Test
    void testSearchProductByName() throws IOException, ServletException {
        // Mocking HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("foodname")).thenReturn("TestFood");

        // Mocking FoodDao findByNameContainingIgnoreCase method
        List<Food> foods = new ArrayList<>();
        Food testFood = new Food();
        testFood.setId(1);
        testFood.setName("TestFood");
        foods.add(testFood);
        when(foodDao.findByNameContainingIgnoreCase("TestFood")).thenReturn(foods);

        // Test the searchProductByName method
        ModelAndView result = foodController.searchProductByName("TestFood");

        // Assertions
        assertEquals("index", result.getViewName());
        assertEquals("yes", result.getModel().get("sentFromOtherSource"));
        assertEquals(foods, result.getModel().get("foods"));
    }

    // Similar test methods for getFood, deleteFood, and updateFood can be added

    @Test
    void testGetFood() throws IOException, ServletException {
        // Mocking HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("foodId")).thenReturn("1");

        // Mocking FoodDao findById method
        Food testFood = new Food();
        testFood.setId(1);
        when(foodDao.findById(1)).thenReturn(Optional.of(testFood));

        // Test the getFood method
        ModelAndView result = foodController.getFood(1);

        // Assertions
        assertEquals("food", result.getViewName());
        assertEquals(testFood, result.getModel().get("food"));
    }

    @Test
    void testDeleteFood() throws IOException, ServletException {
        // Mocking HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("foodId")).thenReturn("1");

        // Mocking FoodDao findById and delete methods
        Food testFood = new Food();
        testFood.setId(1);
        when(foodDao.findById(1)).thenReturn(Optional.of(testFood));

        // Test the deleteFood method
        ModelAndView result = foodController.deleteFood(1);

        // Assertions
        assertEquals("index", result.getViewName());
        assertEquals("Food Deleted Successfully!", result.getModel().get("status"));
    }

    @Test
    void testUpdateFoodPage() throws IOException, ServletException {
        // Mocking HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("foodId")).thenReturn("1");

        // Mocking FoodDao findById method
        Food testFood = new Food();
        testFood.setId(1);
        when(foodDao.findById(1)).thenReturn(Optional.of(testFood));

        // Test the updateFoodPage method
        ModelAndView result = foodController.updateFood(1);

        // Assertions
        assertEquals("updatefood", result.getViewName());
        assertEquals(testFood, result.getModel().get("food"));
    }
}
