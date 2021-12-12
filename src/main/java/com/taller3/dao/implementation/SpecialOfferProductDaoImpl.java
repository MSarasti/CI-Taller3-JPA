package com.taller3.dao.implementation;

import java.util.List;

import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.dao.interfaces.*;
import com.taller3.model.sales.*;

@Repository
@Scope("singleton")
public class SpecialOfferProductDaoImpl implements SpecialOfferProductDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void save(Specialofferproduct specialofferproduct) {
		entityManager.persist(specialofferproduct);
	}

	@Override
	@Transactional
	public void update(Specialofferproduct specialofferproduct) {
		entityManager.merge(specialofferproduct);
	}

	@Override
	@Transactional
	public void delete(Specialofferproduct specialofferproduct) {
		entityManager.remove(specialofferproduct);
	}

	@Override
	@Transactional
	public List<Specialofferproduct> findAll() {
		String jpql = "Select s from Specialofferproduct s";
		return entityManager.createQuery(jpql, Specialofferproduct.class).getResultList();
	}

	@Override
	@Transactional
	public Specialofferproduct findById(SpecialofferproductPK id) {
		return entityManager.find(Specialofferproduct.class, id);
	}

	
}
