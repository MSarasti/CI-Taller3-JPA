package com.taller3.dao.interfaces;

import java.util.List;

import com.taller3.model.prod.Product;

public interface ProductDao {
	void save(Product product);
	void update(Product product);
	void delete(Product product);
	
	List<Product> findAll();
	Product findById(Integer id);
	Product findByProductNumber(String productnumber);	
	List<Product> findByStyle(String style);
}
