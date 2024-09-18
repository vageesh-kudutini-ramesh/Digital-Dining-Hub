package com.onlinefoodorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlinefoodorder.model.Admin;

/**
 * Repository interface for performing CRUD operations on the Admin entity.
 * Extends JpaRepository to inherit basic CRUD functionality from Spring Data JPA.
 */
@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
	
    /**
     * Retrieves an Admin entity by matching the provided email and password.
     *
     * @param emailId The email address of the admin.
     * @param password The password associated with the admin account.
     * @return The Admin entity matching the provided email and password.
     */
	Admin findByEmailidAndPassword(String emailId, String password);

    /**
     * Retrieves an Admin entity by matching the provided email.
     *
     * @param emailId The email address of the admin.
     * @return The Admin entity matching the provided email.
     */
	Admin findByEmailid(String emailId);
}
