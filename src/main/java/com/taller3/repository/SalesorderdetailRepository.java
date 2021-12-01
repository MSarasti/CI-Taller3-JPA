package com.taller3.repository;

import org.springframework.data.repository.CrudRepository;

import com.taller3.model.sales.Salesorderdetail;
import com.taller3.model.sales.SalesorderdetailPK;

public interface SalesorderdetailRepository extends CrudRepository<Salesorderdetail, SalesorderdetailPK> {
	
}
