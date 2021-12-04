package com.taller3.service.interfaces;

import com.taller3.model.sales.Salesorderdetail;
import com.taller3.model.sales.SalesorderdetailPK;

public interface SalesorderdetailService {
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, Integer pId, Integer soId) throws Exception;
	public Salesorderdetail searchSalesOrderDetail(Integer sdId);
	public Salesorderdetail updateSalesOrderDetail(Integer sdId, Salesorderdetail sd) throws Exception;
	public void deleteSalesOrderDetail(Integer sdId);
}
