package com.taller3.dao.implementation;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.dao.interfaces.*;
import com.taller3.model.sales.*;

@Repository
@Transactional
public class SpecialOfferDaoImpl implements SpecialOfferDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Specialoffer specialoffer) {
		entityManager.persist(specialoffer);
	}

	@Override
	public void update(Specialoffer specialoffer) {
		entityManager.merge(specialoffer);
	}

	@Override
	public void delete(Specialoffer specialoffer) {
		entityManager.remove(specialoffer);
	}

	@Override
	public List<Specialoffer> findAll() {
		String jpql = "Select s from Specialoffer s";
		return entityManager.createQuery(jpql, Specialoffer.class).getResultList();
	}

	@Override
	public Specialoffer findById(Integer id) {
		return entityManager.find(Specialoffer.class, id);
	}

	@Override
	public List<Specialoffer> findByStartDate(Timestamp startdate) {
		String jpql = "Select s from Specialoffer s WHERE s.startdate =: startdate";
		return entityManager.createQuery(jpql, Specialoffer.class).setParameter("startdate", startdate).getResultList();
	}

	@Override
	public List<Specialoffer> findByEndDate(Timestamp enddate) {
		String jpql = "Select s from Specialoffer s WHERE s.enddate =: enddate";
		return entityManager.createQuery(jpql, Specialoffer.class).setParameter("enddate", enddate).getResultList();
	}

}
