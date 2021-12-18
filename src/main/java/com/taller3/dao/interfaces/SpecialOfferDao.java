package com.taller3.dao.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.taller3.model.sales.*;

public interface SpecialOfferDao {
	void save(Specialoffer specialoffer);
	void update(Specialoffer specialoffer);
	void delete(Specialoffer specialoffer);
	
	List<Specialoffer> findAll();
	Specialoffer findById(Integer id);
	List<Specialoffer> findByStartDate(LocalDate startdate);	
	List<Specialoffer> findByEndDate(LocalDate enddate);
}
