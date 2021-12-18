package com.taller3.service.interfaces;

import java.time.LocalDate;

import com.taller3.model.sales.Specialoffer;

public interface SpecialofferService {
	public Specialoffer saveSpecialOffer(Specialoffer s);
	public Specialoffer searchSpecialOffer(Integer sId);
	public Specialoffer updateSpecialOffer(Integer sId, Specialoffer s);
	public void deleteSpecialOffer(Integer sId);
	public Specialoffer findById(Integer id);
	public Iterable<Specialoffer> findAll();
	public Iterable<Specialoffer> findByStartDate(LocalDate startdate);
	public Iterable<Specialoffer> findByEndDate(LocalDate enddate);
}
