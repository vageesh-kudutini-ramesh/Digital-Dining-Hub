package com.onlinefoodorder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a Cart in the online food order system.
 */
@Entity
public class Cart {
	
	/**
     * The unique identifier for the Cart.
     */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	/**
     * The identifier of the food item associated with the Cart.
     */
	@Column(name="FoodId")
	private int foodId;
	
	/**
     * The identifier of the user who owns the Cart.
     */
	@Column(name="UserId")
	private int userId;
	
	/**
     * The date when items were added to the Cart.
     */
	@Column(name="Date")
	private String date;
	
	/**
     * The quantity of food items in the Cart.
     */
	@Column(name="Quantity")
	private int quantity;
    
	/**
     * Retrieves the unique identifier of the Cart.
     * @return The Cart's identifier.
     */
	public int getId() {
		return id;
	}
    
	 /**
     * Sets the unique identifier for the Cart.
     * @param id The new identifier for the Cart.
     */
	public void setId(int id) {
		this.id = id;
	}
    
	/**
     * Retrieves the identifier of the user who owns the Cart.
     * @return The identifier of the user.
     */
	public int getUserId() {
		return userId;
	}
    
	/**
     * Sets the identifier of the user who owns the Cart.
     * @param userId The new identifier of the user.
     */
	public void setUserId(int userId) {
		this.userId = userId;
	}
    
	/**
     * Retrieves the date when items were added to the Cart.
     * @return The date when items were added to the Cart.
     */
	public String getDate() {
		return date;
	}
    
	/**
     * Sets the date when items were added to the Cart.
     * @param date The new date when items were added to the Cart.
     */
	public void setDate(String date) {
		this.date = date;
	}
    
	/**
     * Retrieves the quantity of food items in the Cart.
     * @return The quantity of food items.
     */
	public int getQuantity() {
		return quantity;
	}
    
	/**
     * Sets the quantity of food items in the Cart.
     * @param quantity The new quantity of food items.
     */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
	/**
     * Retrieves the identifier of the food item associated with the Cart.
     * @return The identifier of the food item.
     */
	public int getFoodId() {
		return foodId;
	}
    
	/**
     * Sets the identifier of the food item associated with the Cart.
     * @param foodId The new identifier of the food item.
     */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	
}
