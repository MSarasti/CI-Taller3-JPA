package com.taller3.dao.interfaces;

import java.util.List;

import com.taller3.model.sales.*;

public interface SalesOrderDetailDao {
	void save(Salesorderdetail salesorderdetail);
	void update(Salesorderdetail salesorderdetail);
	void delete(Salesorderdetail salesorderdetail);
	
	List<Salesorderdetail> findAll();
	Salesorderdetail findById(Integer id);
	List<Salesorderdetail> findByProductId(Integer pId);
	List<?> findOrderDetailByProductWithMoreThanOneSOP();
}
