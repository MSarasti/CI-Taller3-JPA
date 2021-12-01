package com.taller3.repository;

import org.springframework.data.repository.CrudRepository;

import com.taller3.model.prod.Productcategory;

public interface ProductCategoryRepository extends CrudRepository<Productcategory, Integer> {
	
}
