package com.onlinefoodorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlinefoodorder.model.Category;

/**
 * Repository interface for performing CRUD operations on the Category entity.
 * Extends JpaRepository to inherit basic CRUD functionality from Spring Data JPA.
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

}
