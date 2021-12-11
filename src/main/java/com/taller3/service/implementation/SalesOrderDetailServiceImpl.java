package com.taller3.service.implementation;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller3.dao.implementation.*;
import com.taller3.model.prod.*;
import com.taller3.model.sales.*;
import com.taller3.repository.*;
import com.taller3.service.interfaces.SalesorderdetailService;

@Service
public class SalesOrderDetailServiceImpl implements SalesorderdetailService {
	@Autowired
	public ProductDaoImpl pDao;
	@Autowired
	public SpecialOfferDaoImpl soDao;
	@Autowired
	public SpecialOfferProductDaoImpl sopDao;
	@Autowired
	public SalesOrderDetailDaoImpl sodDao;
	
	public SalesOrderDetailServiceImpl() {
	}

	@Override
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, Integer pId, Integer soId) {
		Specialoffer so = soDao.findById(soId);
		List<Specialofferproduct> offers = so.getSpecialofferproducts();
		Specialofferproduct sop = null;
		for (int i = 0; i < offers.size(); i++) {
			sop = offers.get(i);
			if(sop.getId().getProductid().equals(pId) && sop.getId().getSpecialofferid().equals(soId))
				sd.setSpecialofferproduct(sop);
		}
		sodDao.save(sd);
		return sd;
	}
	
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, SpecialofferproductPK sopId) {
		Specialofferproduct sop = sopDao.findById(sopId);
		sd.setSpecialofferproduct(sop);
		sodDao.save(sd);
		return sd;
	}

	@Override
	public Salesorderdetail searchSalesOrderDetail(Integer sdId) {
		return sodDao.findById(sdId);
	}

	@Override
	public Salesorderdetail updateSalesOrderDetail(Integer sdId, Salesorderdetail sd) {
		Salesorderdetail toChange = sodDao.findById(sdId);
		toChange.setCarriertrackingnumber(sd.getCarriertrackingnumber());
		toChange.setModifieddate(sd.getModifieddate());
		toChange.setOrderqty(sd.getOrderqty());
		toChange.setRowguid(sd.getRowguid());
		toChange.setSpecialofferproduct(sd.getSpecialofferproduct());
		toChange.setUnitprice(sd.getUnitprice());
		toChange.setUnitpricediscount(sd.getUnitpricediscount());
		sodDao.update(toChange);
		
		return sd;
	}

	@Override
	public void deleteSalesOrderDetail(Integer sdId) {
		sodDao.delete(sodDao.findById(sdId));
	}

	public Iterable<Salesorderdetail> findAll(){
		return sodDao.findAll();
	}

	public Salesorderdetail findById(Integer id) {
		return sodDao.findById(id);
	}
}
