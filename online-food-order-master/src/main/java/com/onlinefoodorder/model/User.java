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
 * Entity class representing a user in the online food order system.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;
    
    /**
     * The first name of the user.
     */
    @Column(name="FirstName")
    private String firstname;
    
    /**
     * The last name of the user.
     */
    @Column(name="LastName")
    private String lastname;
    
    /**
     * The email address of the user.
     */
    @Column(name="EmailId")
    private String emailid;
    
    /**
     * The password associated with the user's account.
     */
    @Column(name="Password")
    private String password;
    
    /**
     * The mobile number of the user.
     */
    @Column(name="MobileNo")
    private String mobileno;
    
    /**
     * The street address of the user.
     */
    @Column(name="Street")
    private String street;
    
    /**
     * The city where the user resides.
     */
    @Column(name="City")
    private String city;
    
    /**
     * The pin code or postal code of the user's location.
     */
    @Column(name="Pincode")
    private String pincode;

    /**
     * Retrieves the unique identifier of the user.
     * @return The user's identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     * @param id The new identifier for the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the first name of the user.
     * @return The first name of the user.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the first name for the user.
     * @param firstname The new first name for the user.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Retrieves the last name of the user.
     * @return The last name of the user.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the last name for the user.
     * @param lastname The new last name for the user.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Retrieves the email address of the user.
     * @return The email address of the user.
     */
    public String getEmailid() {
        return emailid;
    }

    /**
     * Sets the email address of the user.
     * @param emailid The new email address for the user.
     */
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    /**
     * Retrieves the password associated with the user's account.
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user's account.
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the mobile number of the user.
     * @return The mobile number of the user.
     */
    public String getMobileno() {
        return mobileno;
    }

    /**
     * Sets the mobile number for the user.
     * @param mobileno The new mobile number for the user.
     */
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    /**
     * Retrieves the street address of the user.
     * @return The street address of the user.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street address for the user.
     * @param street The new street address for the user.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Retrieves the city where the user resides.
     * @return The city where the user resides.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city for the user.
     * @param city The new city for the user.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieves the pin code or postal code of the user's location.
     * @return The pin code or postal code of the user.
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * Sets the pin code or postal code for the user's location.
     * @param pincode The new pin code or postal code for the user.
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
