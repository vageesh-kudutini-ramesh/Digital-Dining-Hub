package com.onlinefoodorder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class representing a category in the online food order system.
 */
@Entity
public class Category {
	
	/**
     * The unique identifier for the category.
     */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	/**
     * The name of the category.
     */
	@Column(name="Name")
	private String name;
	
	/**
     * The description of the category.
     */
	@Column(name="Description")
	private String description;
    
	 /**
     * Retrieves the unique identifier of the category.
     * @return The category's identifier.
     */
	public int getId() {
		return id;
	}
    
	/**
     * Sets the unique identifier for the category.
     * @param id The new identifier for the category.
     */
	public void setId(int id) {
		this.id = id;
	}
    
	/**
     * Retrieves the name of the category.
     * @return The name of the category.
     */
	public String getName() {
		return name;
	}
    
	/**
     * Sets the name for the category.
     * @param name The new name for the category.
     */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
     * Retrieves the description of the category.
     * @return The description of the category.
     */
	public String getDescription() {
		return description;
	}
    
	/**
     * Sets the description for the category.
     * @param description The new description for the category.
     */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
