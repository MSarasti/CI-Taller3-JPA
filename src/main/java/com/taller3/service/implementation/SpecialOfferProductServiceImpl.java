package com.taller3.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller3.dao.implementation.*;
import com.taller3.model.prod.*;
import com.taller3.model.sales.*;
import com.taller3.service.interfaces.SpecialofferproductService;

@Service
public class SpecialOfferProductServiceImpl implements SpecialofferproductService {
	@Autowired
	public ProductDaoImpl pDao;
	@Autowired
	public SpecialOfferDaoImpl soDao;
	@Autowired
	public SpecialOfferProductDaoImpl sopDao;
	
	public SpecialOfferProductServiceImpl() {
	}

	@Override
	@Transactional
	public Specialofferproduct saveSpecialOfferProduct(Specialofferproduct sp, Integer pId, Integer soId) {
		Product p = pDao.findById(pId);
		Specialoffer so = soDao.findById(soId);
		sp.setSpecialoffer(so);
		sp.getId().setProductid(p.getProductid());
		sp.getId().setSpecialofferid(soId);
		sopDao.save(sp);
		
		return sp;
	}

	@Override
	@Transactional
	public Specialofferproduct searchSpecialOfferProduct(SpecialofferproductPK spId) {
		return sopDao.findById(spId);
	}

	@Override
	@Transactional
	public Specialofferproduct updateSpecialOfferProduct(SpecialofferproductPK spId, Specialofferproduct sp) {
		Specialofferproduct toChange = sopDao.findById(spId);
		toChange.setModifieddate(sp.getModifieddate());
		toChange.setRowguid(sp.getRowguid());
		toChange.setSpecialoffer(sp.getSpecialoffer());
		sopDao.update(toChange);
		return sp;
	}

	@Override
	@Transactional
	public void deleteSpecialOfferProduct(SpecialofferproductPK spId) {
		sopDao.delete(sopDao.findById(spId));
	}

	@Override
	@Transactional
	public Specialofferproduct findById(SpecialofferproductPK id) {
		return sopDao.findById(id);
	}

	@Override
	@Transactional
	public Iterable<Specialofferproduct> findAll(){
		return sopDao.findAll();
	}
}
