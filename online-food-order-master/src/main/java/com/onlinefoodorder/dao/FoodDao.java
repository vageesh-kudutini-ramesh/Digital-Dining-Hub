package com.onlinefoodorder.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.onlinefoodorder.model.Food;

/**
 * Repository interface for performing CRUD operations on the Food entity.
 * Extends JpaRepository to inherit basic CRUD functionality from Spring Data JPA.
 */
@Repository
public interface FoodDao extends JpaRepository<Food, Integer> {
    
    /**
     * Retrieves a list of Food entities by matching the provided name (case-insensitive).
     *
     * @param name The name or part of the name of the food item to search for.
     * @return A list of Food entities whose names contain the provided search string.
     */
    List<Food> findByNameContainingIgnoreCase(String name);

    /**
     * Retrieves a list of Food entities by matching the provided category ID.
     *
     * @param categoryId The identifier of the category whose food items are to be retrieved.
     * @return A list of Food entities associated with the provided category ID.
     */
    List<Food> findByCategoryId(int categoryId);

    /**
     * Deletes Food entities by executing a custom JPQL query based on the provided category ID.
     *
     * @param categoryId The identifier of the category whose food items are to be deleted.
     */
    @Query(value = "delete from Food f where f.categoryId = :categoryId")
    void deleteProductByCategoryId(@Param("categoryId") int categoryId);

}
