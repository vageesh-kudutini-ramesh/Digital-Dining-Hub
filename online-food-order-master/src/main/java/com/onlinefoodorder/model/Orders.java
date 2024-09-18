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
 * Entity class representing an order in the online food order system.
 */
@Entity
public class Orders {
	
	/**
     * The unique identifier for the order.
     */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	/**
     * The order ID, a unique identifier for the order.
     */
	@Column(name="OrderId")
	private String orderId;
	
	/**
     * The identifier of the user who placed the order.
     */
	@Column(name="UserId")
	private int userId;
	
	/**
     * The identifier of the food item included in the order.
     */
	@Column(name="FoodId")
	private int foodId;
	
	/**
     * The quantity of the food item ordered.
     */
	@Column(name="Quantity")
	private int quantity;
	
	/**
     * The date when the order was placed.
     */
	@Column(name="OrderDate")
	private String orderDate;
	
	/**
     * The expected delivery date for the order.
     */
	@Column(name="DeliveryDate")
	private String deliveryDate;
	
	/**
     * The current delivery status of the order.
     */
	@Column(name="DeliveryStatus")
	private String deliveryStatus;
    
	/**
     * Retrieves the unique identifier of the order.
     * @return The order's identifier.
     */
	public int getId() {
		return id;
	}
    
	/**
     * Sets the unique identifier for the order.
     * @param id The new identifier for the order.
     */
	public void setId(int id) {
		this.id = id;
	}
    
	 /**
     * Retrieves the order ID, a unique identifier for the order.
     * @return The order ID.
     */
	public String getOrderId() {
		return orderId;
	}
    
	/**
     * Sets the order ID for the order.
     * @param orderId The new order ID.
     */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    
	/**
     * Retrieves the identifier of the user who placed the order.
     * @return The identifier of the user.
     */
	public int getUserId() {
		return userId;
	}
    
	/**
     * Sets the identifier of the user who placed the order.
     * @param userId The new identifier of the user.
     */
	public void setUserId(int userId) {
		this.userId = userId;
	}
    
	 /**
     * Retrieves the identifier of the food item included in the order.
     * @return The identifier of the food item.
     */
	public int getFoodId() {
		return foodId;
	}
    
	/**
     * Sets the identifier of the food item included in the order.
     * @param foodId The new identifier of the food item.
     */
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
    
	 /**
     * Retrieves the quantity of the food item ordered.
     * @return The quantity of the food item.
     */
	public int getQuantity() {
		return quantity;
	}
    
	 /**
     * Sets the quantity of the food item ordered.
     * @param quantity The new quantity of the food item.
     */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
	/**
     * Retrieves the date when the order was placed.
     * @return The date when the order was placed.
     */
	public String getOrderDate() {
		return orderDate;
	}
    
	/**
     * Sets the date when the order was placed.
     * @param orderDate The new date when the order was placed.
     */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
    
	 /**
     * Retrieves the expected delivery date for the order.
     * @return The expected delivery date.
     */
	public String getDeliveryDate() {
		return deliveryDate;
	}
    
	/**
     * Sets the expected delivery date for the order.
     * @param deliveryDate The new expected delivery date.
     */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
    
	 /**
     * Retrieves the current delivery status of the order.
     * @return The delivery status of the order.
     */
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
    
	 /**
     * Sets the current delivery status of the order.
     * @param deliveryStatus The new delivery status of the order.
     */
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
}
