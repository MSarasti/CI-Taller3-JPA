package com.taller3.service.interfaces;

import com.taller3.model.sales.*;

public interface SpecialofferproductService {
	public Specialofferproduct saveSpecialOfferProduct(Specialofferproduct sp, Integer pId, Integer soId);
	public Specialofferproduct searchSpecialOfferProduct(SpecialofferproductPK spId);
	public Specialofferproduct updateSpecialOfferProduct(SpecialofferproductPK spId, Specialofferproduct sp);
	public void deleteSpecialOfferProduct(SpecialofferproductPK spId);
	Specialofferproduct findById(SpecialofferproductPK id);
	Iterable<Specialofferproduct> findAll();
}
