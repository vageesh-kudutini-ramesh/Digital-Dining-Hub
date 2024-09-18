package com.onlinefoodorder.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinefoodorder.model.Orders;

/**
 * Repository interface for performing CRUD operations on the Orders entity.
 * Extends JpaRepository to inherit basic CRUD functionality from Spring Data JPA.
 */
@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {
    
    /**
     * Retrieves a list of Orders entities by matching the provided order ID.
     *
     * @param orderId The unique identifier of the order to search for.
     * @return A list of Orders entities with the provided order ID.
     */
    List<Orders> findByOrderId(String orderId);

    /**
     * Retrieves a list of Orders entities by matching the provided user ID.
     *
     * @param userId The identifier of the user whose orders are to be retrieved.
     * @return A list of Orders entities associated with the provided user ID.
     */
    List<Orders> findByUserId(int userId);

    /**
     * Retrieves a list of Orders entities by matching the provided order date.
     *
     * @param orderDate The date when the orders were placed.
     * @return A list of Orders entities with the provided order date.
     */
    List<Orders> findByOrderDate(String orderDate);

    /**
     * Retrieves a list of Orders entities by matching the provided order date and user ID.
     *
     * @param orderDate The date when the orders were placed.
     * @param userId The identifier of the user whose orders are to be retrieved.
     * @return A list of Orders entities associated with the provided order date and user ID.
     */
    List<Orders> findByOrderDateAndUserId(String orderDate, int userId);
}
