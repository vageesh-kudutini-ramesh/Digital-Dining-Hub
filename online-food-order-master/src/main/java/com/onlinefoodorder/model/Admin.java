package com.onlinefoodorder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class representing an Admin in the online food order system.
 */
@Entity
public class Admin {
	
	/**
     * The unique identifier for the Admin.
     */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	 /**
     * The first name of the Admin.
     */
	@Column(name="FirstName")
	private String firstname;
	
	 /**
     * The last name of the Admin.
     */
	@Column(name="LastName")
	private String lastname;
	
	 /**
     * The email address of the Admin.
     */
	@Column(name="EmailId")
	private String emailid;
	
	/**
     * The password associated with the Admin's account.
     */
	@Column(name="Password")
	private String password;
	
	/**
     * The mobile number of the Admin.
     */
	@Column(name="MobileNo")
	private String mobileno;
	
	/**
     * The street address of the Admin.
     */
	@Column(name="Street")
	private String street;
	
	/**
     * The city where the Admin resides.
     */
	@Column(name="City")
	private String city;
	
	/**
     * The pin code or postal code of the Admin's location.
     */
	@Column(name="Pincode")
	private String pincode;
    
	 /**
     * Retrieves the unique identifier of the Admin.
     * @return The Admin's identifier.
     */
	public int getId() {
		return id;
	}
    
	/**
     * Sets the unique identifier for the Admin.
     * @param id The new identifier for the Admin.
     */
	public void setId(int id) {
		this.id = id;
	}
    
	/**
     * Retrieves the first name of the Admin.
     * @return The first name of the Admin.
     */
	public String getFirstname() {
		return firstname;
	}
    
	/**
     * Sets the first name of the Admin.
     * @param firstname The new first name for the Admin.
     */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
    
	/**
     * Retrieves the last name of the Admin.
     * @return The last name of the Admin.
     */
	public String getLastname() {
		return lastname;
	}
    
	 /**
     * Sets the last name of the Admin.
     * @param lastname The new last name for the Admin.
     */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
    
	/**
     * Retrieves the email address of the Admin.
     * @return The email address of the Admin.
     */
	public String getEmailid() {
		return emailid;
	}
    
	/**
     * Sets the email address of the Admin.
     * @param emailid The new email address for the Admin.
     */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
    
	 /**
     * Retrieves the password associated with the Admin's account.
     * @return The Admin's password.
     */
	public String getPassword() {
		return password;
	}
    
	/**
     * Sets the password for the Admin's account.
     * @param password The new password for the Admin.
     */
	public void setPassword(String password) {
		this.password = password;
	}
    
	/**
     * Retrieves the mobile number of the Admin.
     * @return The mobile number of the Admin.
     */
	public String getMobileno() {
		return mobileno;
	}
    
	/**
     * Sets the mobile number for the Admin.
     * @param mobileno The new mobile number for the Admin.
     */
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
    
	/**
     * Retrieves the street address of the Admin.
     * @return The street address of the Admin.
     */
	public String getStreet() {
		return street;
	}
    
	/**
     * Sets the street address for the Admin.
     * @param street The new street address for the Admin.
     */
	public void setStreet(String street) {
		this.street = street;
	}
    
	/**
     * Retrieves the city where the Admin resides.
     * @return The city where the Admin resides.
     */
	public String getCity() {
		return city;
	}
    
	/**
     * Sets the city for the Admin.
     * @param city The new city for the Admin.
     */
	public void setCity(String city) {
		this.city = city;
	}
    
	/**
     * Retrieves the pin code or postal code of the Admin's location.
     * @return The pin code or postal code of the Admin.
     */
	public String getPincode() {
		return pincode;
	}
    
	/**
     * Sets the pin code or postal code for the Admin's location.
     * @param pincode The new pin code or postal code for the Admin.
     */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
}
