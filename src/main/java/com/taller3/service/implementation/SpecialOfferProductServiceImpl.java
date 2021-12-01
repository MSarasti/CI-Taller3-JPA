package com.taller3.service.implementation;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller3.model.prod.*;
import com.taller3.model.sales.*;
import com.taller3.repository.*;
import com.taller3.service.interfaces.SpecialofferproductService;

@Service
public class SpecialOfferProductServiceImpl implements SpecialofferproductService {
	@Autowired
	public ProductRepository pRep;
	@Autowired
	public SpecialofferRepository soRep;
	@Autowired
	public SpecialofferproductRepository sopRep;
	
	public SpecialOfferProductServiceImpl() {
	}

	@Override
	public Specialofferproduct saveSpecialOfferProduct(Specialofferproduct sp, Integer pId, Integer soId) {
		Product p = pRep.findById(pId).get();
		Specialoffer so = soRep.findById(soId).get();
		sp.setSpecialoffer(so);
		sp.getId().setProductid(pId);
		sp.getId().setSpecialofferid(soId);
		sopRep.save(sp);
		
		return sp;
	}

	@Override
	public Specialofferproduct searchSpecialOfferProduct(SpecialofferproductPK spId) {
		Optional<Specialofferproduct> op = sopRep.findById(spId);
		return (op.isEmpty()) ? null : op.get();
	}

	@Override
	public Specialofferproduct updateSpecialOfferProduct(SpecialofferproductPK spId, Specialofferproduct sp) {
		Specialofferproduct toChange = sopRep.findById(spId).get();
		toChange.setModifieddate(sp.getModifieddate());
		toChange.setRowguid(sp.getRowguid());
		toChange.setSpecialoffer(sp.getSpecialoffer());
		sopRep.save(toChange);
		return sp;
	}

	@Override
	public void deleteSpecialOfferProduct(SpecialofferproductPK spId) {
		sopRep.delete(sopRep.findById(spId).get());
	}

	public Iterable<Specialofferproduct> findAll(){
		return sopRep.findAll();
	}

	public Optional<Specialofferproduct> findById(SpecialofferproductPK id) {
		return sopRep.findById(id);
	}
}
