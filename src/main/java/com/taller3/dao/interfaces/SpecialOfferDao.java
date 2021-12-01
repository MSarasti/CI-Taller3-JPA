package com.taller3.dao.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.taller3.model.sales.*;

public interface SpecialOfferDao {
	void save(Specialoffer specialoffer);
	void update(Specialoffer specialoffer);
	void delete(Specialoffer specialoffer);
	
	List<Specialoffer> findAll();
	Specialoffer findById(SpecialofferproductPK id);
	List<Specialoffer> findByStartDate(Timestamp startdate);	
	List<Specialoffer> findByEndDate(Timestamp enddate);
}
