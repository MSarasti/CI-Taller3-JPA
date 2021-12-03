package com.taller3.dao.implementation;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.dao.interfaces.ProductDao;
import com.taller3.model.prod.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Product product) {
		entityManager.persist(product);
	}

	@Override
	public void update(Product product) {
		entityManager.merge(product);
	}

	@Override
	public void delete(Product product) {
		entityManager.remove(product);
	}

	@Override
	public List<Product> findAll() {
		String jpql = "Select p from Product p";
		return entityManager.createQuery(jpql, Product.class).getResultList();
	}

	@Override
	public Product findById(Integer id) {
		return entityManager.find(Product.class, id);
	}

	@Override
	public Product findByProductNumber(String productnumber) {
		return entityManager.find(Product.class, productnumber);
	}

	@Override
	public List<Product> findByStyle(String style) {
		String jpql = "Select p from Product p WHERE p.style =: style";
		return entityManager.createQuery(jpql, Product.class).setParameter("style", style).getResultList();
	}

}
