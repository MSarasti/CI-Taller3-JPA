package com.taller3.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.dao.interfaces.SalesOrderDetailDao;
import com.taller3.model.sales.Salesorderdetail;

@Repository
@Transactional
public class SalesOrderDetailDaoImpl implements SalesOrderDetailDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Salesorderdetail salesorderdetail) {
		entityManager.persist(salesorderdetail);
	}

	@Override
	public void update(Salesorderdetail salesorderdetail) {
		entityManager.merge(salesorderdetail);
	}

	@Override
	public void delete(Salesorderdetail salesorderdetail) {
		entityManager.remove(salesorderdetail);
	}

	@Override
	public List<Salesorderdetail> findAll() {
		String jpql = "Select s from Salesorderdetail s";
		return entityManager.createQuery(jpql, Salesorderdetail.class).getResultList();
	}

	@Override
	public Salesorderdetail findById(Integer id) {
		return entityManager.find(Salesorderdetail.class, id);
	}

	@Override
	public List<Salesorderdetail> findByProductId(Integer pId) {
		String jpql = "Select s from Salesorderdetail s WHERE s.specialofferproduct.productid := pId";
		return entityManager.createQuery(jpql, Salesorderdetail.class).setParameter("pId", pId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findOrderDetailByProductWithMoreThanOneSOP() {
		String jpql = "Select s, p.name from Salesorderdetail s WHERE (Select COUNT(sop) FROM s.specialofferproduct sop, Product p WHERE sop.id.productid=p.productid) > 1 ORDER BY p.name ASC";
		return entityManager.createQuery(jpql).getResultList();
	}

}