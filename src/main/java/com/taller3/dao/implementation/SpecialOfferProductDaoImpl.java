package com.taller3.dao.implementation;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.dao.interfaces.*;
import com.taller3.model.sales.*;

@Repository
@Transactional
public class SpecialOfferProductDaoImpl implements SpecialOfferProductDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Specialofferproduct specialofferproduct) {
		entityManager.persist(specialofferproduct);
	}

	@Override
	public void update(Specialofferproduct specialofferproduct) {
		entityManager.merge(specialofferproduct);
	}

	@Override
	public void delete(Specialofferproduct specialofferproduct) {
		entityManager.remove(specialofferproduct);
	}

	@Override
	public List<Specialofferproduct> findAll() {
		String jpql = "Select s from Specialofferproduct s";
		return entityManager.createQuery(jpql, Specialofferproduct.class).getResultList();
	}

	@Override
	public Specialofferproduct findById(SpecialofferproductPK id) {
		return entityManager.find(Specialofferproduct.class, id);
	}

}
