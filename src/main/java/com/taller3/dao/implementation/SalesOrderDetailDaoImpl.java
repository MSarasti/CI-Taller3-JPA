package com.taller3.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.dao.interfaces.SalesOrderDetailDao;
import com.taller3.model.sales.Salesorderdetail;

@Repository
@Scope("singleton")
public class SalesOrderDetailDaoImpl implements SalesOrderDetailDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Salesorderdetail salesorderdetail) {
		entityManager.persist(salesorderdetail);
	}

	@Override
	@Transactional
	public void update(Salesorderdetail salesorderdetail) {
		entityManager.merge(salesorderdetail);
	}

	@Override
	@Transactional
	public void delete(Salesorderdetail salesorderdetail) {
		entityManager.remove(salesorderdetail);
	}

	@Override
	@Transactional
	public List<Salesorderdetail> findAll() {
		String jpql = "Select s from Salesorderdetail s";
		return entityManager.createQuery(jpql, Salesorderdetail.class).getResultList();
	}

	@Override
	@Transactional
	public Salesorderdetail findById(Integer id) {
		return entityManager.find(Salesorderdetail.class, id);
	}

	@Override
	@Transactional
	public List<Salesorderdetail> findByProductId(Integer pId) {
		String jpql = "Select s from Salesorderdetail s WHERE s.specialofferproduct.specialoffer.product.productid := pId";
		return entityManager.createQuery(jpql, Salesorderdetail.class).setParameter("pId", pId).getResultList();
	}
	
	@Override
	@Transactional
	public List<?> findOrderDetailByProductWithMoreThanOneSOP() {
		String jpql = "Select s, p.name from Salesorderdetail s WHERE (Select COUNT(sop) FROM s.specialofferproduct sop, Product p WHERE sop.id.productid=p.productid) > 1 ORDER BY p.name ASC";
		return entityManager.createQuery(jpql).getResultList();
	}

}
