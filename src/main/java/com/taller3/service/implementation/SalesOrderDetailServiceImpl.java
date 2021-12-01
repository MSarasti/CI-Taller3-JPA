package com.taller3.service.implementation;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller3.model.prod.*;
import com.taller3.model.sales.*;
import com.taller3.repository.*;
import com.taller3.service.interfaces.SalesorderdetailService;

@Service
public class SalesOrderDetailServiceImpl implements SalesorderdetailService {
	@Autowired
	public ProductRepository pRep;
	@Autowired
	public SpecialofferRepository soRep;
	@Autowired
	public SpecialofferproductRepository sopRep;
	@Autowired
	public SalesorderdetailRepository sodRep;
	
	public SalesOrderDetailServiceImpl() {
	}

	@Override
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, Integer pId, Integer soId) {
		Specialoffer so = soRep.findById(soId).get();
		Specialofferproduct sop = sopRep.findBySpecialoffer(so).get();
		if(sop.getId().getProductid().equals(pId) && sop.getId().getSpecialofferid().equals(soId))
			sd.setSpecialofferproduct(sop);
		sodRep.save(sd);
		return sd;
	}
	
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, SpecialofferproductPK sopId) {
		Specialofferproduct sop = sopRep.findById(sopId).get();
		sd.setSpecialofferproduct(sop);
		sodRep.save(sd);
		return sd;
	}

	@Override
	public Salesorderdetail searchSalesOrderDetail(SalesorderdetailPK sdId) {
		Optional<Salesorderdetail> op = sodRep.findById(sdId);
		return (op.isEmpty()) ? null : op.get();
	}

	@Override
	public Salesorderdetail updateSalesOrderDetail(SalesorderdetailPK sdId, Salesorderdetail sd) {
		Salesorderdetail toChange = sodRep.findById(sdId).get();
		toChange.setCarriertrackingnumber(sd.getCarriertrackingnumber());
		toChange.setModifieddate(sd.getModifieddate());
		toChange.setOrderqty(sd.getOrderqty());
		toChange.setRowguid(sd.getRowguid());
		toChange.setSpecialofferproduct(sd.getSpecialofferproduct());
		toChange.setUnitprice(sd.getUnitprice());
		toChange.setUnitpricediscount(sd.getUnitpricediscount());
		sodRep.save(toChange);
		
		return sd;
	}

	@Override
	public void deleteSalesOrderDetail(SalesorderdetailPK sdId) {
		sodRep.delete(sodRep.findById(sdId).get());
	}

	public Iterable<Salesorderdetail> findAll(){
		return sodRep.findAll();
	}

	public Optional<Salesorderdetail> findById(SalesorderdetailPK id) {
		return sodRep.findById(id);
	}
}
