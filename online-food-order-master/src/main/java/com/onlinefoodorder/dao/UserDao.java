package com.onlinefoodorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlinefoodorder.model.User;

/**
 * Repository interface for performing CRUD operations on the User entity.
 * Extends JpaRepository to inherit basic CRUD functionality from Spring Data JPA.
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    
    /**
     * Retrieves a User entity by matching the provided email and password.
     *
     * @param emailId The email address of the user.
     * @param password The password associated with the user account.
     * @return The User entity matching the provided email and password.
     */
    User findByEmailidAndPassword(String emailId, String password);

    /**
     * Retrieves a User entity by matching the provided email.
     *
     * @param emailId The email address of the user.
     * @return The User entity matching the provided email.
     */
    User findByEmailid(String emailId);

    /**
     * Retrieves a User entity by matching the provided email and mobile number.
     *
     * @param emailId The email address of the user.
     * @param mobileNo The mobile number associated with the user account.
     * @return The User entity matching the provided email and mobile number.
     */
    User findByEmailidAndMobileno(String emailId, String mobileNo);
}
