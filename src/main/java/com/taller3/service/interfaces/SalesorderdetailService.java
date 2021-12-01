package com.taller3.service.interfaces;

import com.taller3.model.sales.Salesorderdetail;
import com.taller3.model.sales.SalesorderdetailPK;

public interface SalesorderdetailService {
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, Integer pId, Integer soId) throws Exception;
	public Salesorderdetail searchSalesOrderDetail(SalesorderdetailPK sdId);
	public Salesorderdetail updateSalesOrderDetail(SalesorderdetailPK sdId, Salesorderdetail sd) throws Exception;
	public void deleteSalesOrderDetail(SalesorderdetailPK sdId);
}
