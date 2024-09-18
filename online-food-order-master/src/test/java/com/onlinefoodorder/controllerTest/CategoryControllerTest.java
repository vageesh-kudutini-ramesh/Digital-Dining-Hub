package com.onlinefoodorder.controllerTest;

import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.onlinefoodorder.controller.CategoryController;
import com.onlinefoodorder.dao.CategoryDao;
import com.onlinefoodorder.dao.FoodDao;
import com.onlinefoodorder.model.Category;
import com.onlinefoodorder.model.Food;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodDao foodDao;

    @MockBean
    private CategoryDao categoryDao;

    @Test
    public void testAddCategory() throws Exception {
        Category category = new Category();
        category.setName("TestCategory");

        mockMvc.perform(post("/addcategory")
                .flashAttr("category", category))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("status"))
                .andExpect(view().name("index"));

        verify(categoryDao, times(1)).save(category);
    }

    @Test
    public void testDeleteCategory() throws Exception {
        int categoryId = 1;

        // Mock behavior of categoryDao.findById
        when(categoryDao.findById(categoryId)).thenReturn(Optional.of(new Category()));

        // Mock behavior of foodDao.findByCategoryId
        when(foodDao.findByCategoryId(categoryId)).thenReturn(Collections.singletonList(new Food()));

        mockMvc.perform(get("/deletecategory")
                .param("categoryId", String.valueOf(categoryId)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("status"))
                .andExpect(view().name("index"));

        // Verify that foodDao.delete is invoked for each associated food
        verify(foodDao, times(1)).delete(any(Food.class));
        
        // Verify that categoryDao.delete is invoked with the correct category
        verify(categoryDao, times(1)).delete(any(Category.class));
    }


    

    @Test
    public void testCategory() throws Exception {
        int categoryId = 1;

        mockMvc.perform(get("/category")
                .param("categoryId", String.valueOf(categoryId)))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("foods"))
                .andExpect(model().attributeExists("sentFromOtherSource"))
                .andExpect(view().name("index"));

        verify(foodDao, times(1)).findByCategoryId(categoryId);
    }
}
