package com.taller3.service.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller3.dao.implementation.SpecialOfferDaoImpl;
import com.taller3.model.sales.Specialoffer;
import com.taller3.service.interfaces.SpecialofferService;

@Service
public class SpecialOfferServiceImpl implements SpecialofferService {
	
	@Autowired
	private SpecialOfferDaoImpl soDao;
	
	public SpecialOfferServiceImpl(SpecialOfferDaoImpl soDao) {
		this.soDao = soDao;
	}

	@Override
	public Specialoffer saveSpecialOffer(Specialoffer s) {
		soDao.save(s);
		return s;
	}

	@Override
	public Specialoffer searchSpecialOffer(Integer sId) {
		return soDao.findById(sId);
	}

	@Override
	public Specialoffer updateSpecialOffer(Integer sId, Specialoffer s) {
		Specialoffer toChange = soDao.findById(sId);
		String cat = s.getCategory();
		BigDecimal dis = s.getDiscountpct();
		LocalDate mod = s.getModifieddate();
		
		toChange.setCategory(cat);
		toChange.setDiscountpct(dis);
		toChange.setModifieddate(mod);
		toChange.setDescription(s.getDescription());
		toChange.setEnddate(s.getEnddate());
		toChange.setMaxqty(s.getMaxqty());
		toChange.setMinqty(s.getMinqty());
		toChange.setRowguid(s.getRowguid());
		toChange.setSpecialofferproducts(s.getSpecialofferproducts());
		toChange.setStartdate(s.getStartdate());
		toChange.setEnddate(s.getEnddate());
		toChange.setType(s.getType());
		soDao.update(toChange);
		return s;
	}

	@Override
	public void deleteSpecialOffer(Integer sId) {
		soDao.delete(soDao.findById(sId));
	}

	public Iterable<Specialoffer> findAll() {
		return soDao.findAll();
	}
	
	public Iterable<Specialoffer> findByStartDate(Timestamp startdate) {
		return soDao.findByStartDate(startdate);
	}
	
	public Iterable<Specialoffer> findByEndDate(Timestamp enddate) {
		return soDao.findByEndDate(enddate);
	}

	public Specialoffer findById(Integer id) {
		return soDao.findById(id);
	}
}
