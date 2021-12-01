package com.taller3.service.interfaces;

import com.taller3.model.sales.Specialofferproduct;
import com.taller3.model.sales.SpecialofferproductPK;

public interface SpecialofferproductService {
	public Specialofferproduct saveSpecialOfferProduct(Specialofferproduct sp, Integer pId, Integer soId) throws Exception;
	public Specialofferproduct searchSpecialOfferProduct(SpecialofferproductPK spId);
	public Specialofferproduct updateSpecialOfferProduct(SpecialofferproductPK spId, Specialofferproduct sp) throws Exception;
	public void deleteSpecialOfferProduct(SpecialofferproductPK spId);
}
