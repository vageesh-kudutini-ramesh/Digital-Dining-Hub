/**
 * Controller class for managing categories and associated actions.
 */
package com.onlinefoodorder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodorder.dao.CategoryDao;
import com.onlinefoodorder.dao.FoodDao;
import com.onlinefoodorder.model.Category;
import com.onlinefoodorder.model.Food;

@Controller
public class CategoryController {
	
	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	/**
     * Handles the HTTP POST request for adding a new category.
     *
     * @param category The Category object to be added.
     * @return ModelAndView object representing the view and model for the response.
     */
	@PostMapping("/addcategory")
	public ModelAndView addcategory(@ModelAttribute Category category) {
		ModelAndView  mv = new ModelAndView();
		
		categoryDao.save(category);
		
		mv.addObject("status", "Category Added Successfully!");
		mv.setViewName("index");
		
		return mv;
	}
	
	/*
	@GetMapping("/deletecategory")
	public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) {
        ModelAndView  mv = new ModelAndView();
		
        Category category = null;
		Optional<Category> o = categoryDao.findById(categoryId);
		
		if(o.isPresent()) {
			category = o.get();
		}
		
		categoryDao.delete(category);
        
		List<Food> foods = foodDao.findByCategoryId(categoryId);
		
		for(Food food : foods) {
			foodDao.delete(food);
		}
		
		mv.addObject("status", "Category Deleted Successfully!");
		mv.setViewName("index");
		
		return mv;
	}
	*/
	
	/**
     * Handles the HTTP GET request for deleting a category and its associated foods.
     *
     * @param categoryId The ID of the category to be deleted.
     * @return ModelAndView object representing the view and model for the response.
     */
	@GetMapping("/deletecategory")
	public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) {
	    ModelAndView mv = new ModelAndView();

	    // Use categoryDao to find the category
	    Optional<Category> optionalCategory = categoryDao.findById(categoryId);

	    if (optionalCategory.isPresent()) {
	        Category category = optionalCategory.get();

	        // Retrieve and delete associated foods
	        List<Food> foods = foodDao.findByCategoryId(categoryId);
	        for (Food food : foods) {
	            foodDao.delete(food);
	        }

	        // Delete the category after deleting associated foods
	        categoryDao.delete(category);

	        mv.addObject("status", "Category Deleted Successfully!");
	    } else {
	        mv.addObject("status", "Category Not Found!");
	    }

	    mv.setViewName("index");

	    return mv;
	}

	/**
     * Handles the HTTP GET request for displaying foods based on the selected category.
     *
     * @param categoryId The ID of the selected category.
     * @return ModelAndView object representing the view and model for the response.
     */
	@GetMapping("/category")
	public ModelAndView category(@RequestParam("categoryId") int  categoryId) {
		ModelAndView mv = new ModelAndView();
		List<Food> foods = new ArrayList<>();
		if(categoryId == 0) {
			foods = foodDao.findAll();
		}
		
		else {
			foods = foodDao.findByCategoryId(categoryId);
		}
		
		mv.addObject("foods", foods);
		mv.addObject("sentFromOtherSource", "yes");
		mv.setViewName("index");
		
		return mv;
	}

}
