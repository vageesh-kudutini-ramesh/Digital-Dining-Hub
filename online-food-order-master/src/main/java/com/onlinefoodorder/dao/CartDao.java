package com.onlinefoodorder.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinefoodorder.model.Cart;

/**
 * Repository interface for performing CRUD operations on the Cart entity.
 * Extends JpaRepository to inherit basic CRUD functionality from Spring Data JPA.
 */
@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
    
    /**
     * Retrieves a list of Cart entities by matching the provided user ID.
     *
     * @param userId The identifier of the user whose carts are to be retrieved.
     * @return A list of Cart entities associated with the provided user ID.
     */
    List<Cart> findByUserId(int userId);

    /**
     * Counts the number of Cart entities by matching the provided user ID.
     *
     * @param userId The identifier of the user whose cart count is to be determined.
     * @return The count of Cart entities associated with the provided user ID.
     */
    Long countByUserId(int userId);
}
