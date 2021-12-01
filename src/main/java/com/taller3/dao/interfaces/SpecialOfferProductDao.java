package com.taller3.dao.interfaces;

import java.util.List;

import com.taller3.model.sales.*;

public interface SpecialOfferProductDao {
	void save(Specialofferproduct specialofferproduct);
	void update(Specialofferproduct specialofferproduct);
	void delete(Specialofferproduct specialofferproduct);
	
	List<Specialofferproduct> findAll();
	Specialofferproduct findById(Integer id);
}
