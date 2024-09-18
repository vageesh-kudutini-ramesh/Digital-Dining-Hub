package com.onlinefoodorder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a food item in the online food order system.
 */
@Entity
public class Food {
	
	/**
     * The unique identifier for the food item.
     */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	/**
     * The name of the food item.
     */
	@Column(name="Name")
	private String name;
	
	/**
     * The description of the food item.
     */
	@Column(name="Description")
	private String description;
	
	/**
     * The price of the food item.
     */
	@Column(name="Price")
	private double price;
	
	 /**
     * The discount applied to the food item.
     */
	@Column(name="Discount")
	private double discount;
	
	/**
     * The file path to the image representing the food item.
     */
	@Column(name="ImagePath")
	private String imagePath;
    
	/**
     * The identifier of the category to which the food item belongs.
     */
	@Column(name="CategoryId")
	private int categoryId;
    
	/**
     * Retrieves the unique identifier of the food item.
     * @return The food item's identifier.
     */
	public int getId() {
		return id;
	}
    
	/**
     * Sets the unique identifier for the food item.
     * @param id The new identifier for the food item.
     */
	public void setId(int id) {
		this.id = id;
	}
    
	 /**
     * Retrieves the name of the food item.
     * @return The name of the food item.
     */
	public String getName() {
		return name;
	}
    
	 /**
     * Sets the name for the food item.
     * @param name The new name for the food item.
     */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
     * Retrieves the description of the food item.
     * @return The description of the food item.
     */
	public String getDescription() {
		return description;
	}
    
	 /**
     * Sets the description for the food item.
     * @param description The new description for the food item.
     */
	public void setDescription(String description) {
		this.description = description;
	}
    
	 /**
     * Retrieves the price of the food item.
     * @return The price of the food item.
     */
	public double getPrice() {
		return price;
	}
    
	 /**
     * Sets the price for the food item.
     * @param price The new price for the food item.
     */
	public void setPrice(double price) {
		this.price = price;
	}
    
	 /**
     * Retrieves the discount applied to the food item.
     * @return The discount applied to the food item.
     */
	public double getDiscount() {
		return discount;
	}
    
	/**
     * Sets the discount for the food item.
     * @param discount The new discount for the food item.
     */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
    
	 /**
     * Retrieves the file path to the image representing the food item.
     * @return The file path to the image.
     */
	public String getImagePath() {
		return imagePath;
	}
    
	/**
     * Sets the file path to the image representing the food item.
     * @param imagePath The new file path to the image.
     */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
    
	/**
     * Retrieves the identifier of the category to which the food item belongs.
     * @return The identifier of the category.
     */
	public int getCategoryId() {
		return categoryId;
	}
    
	/**
     * Sets the identifier of the category for the food item.
     * @param categoryId The new identifier of the category.
     */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
