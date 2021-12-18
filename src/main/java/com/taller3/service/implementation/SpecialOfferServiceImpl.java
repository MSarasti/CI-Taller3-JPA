package com.taller3.service.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Specialoffer saveSpecialOffer(Specialoffer s) {
		soDao.save(s);
		return s;
	}

	@Override
	@Transactional
	public Specialoffer searchSpecialOffer(Integer sId) {
		return soDao.findById(sId);
	}

	@Override
	@Transactional
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
	@Transactional
	public void deleteSpecialOffer(Integer sId) {
		soDao.delete(soDao.findById(sId));
	}

	@Override
	@Transactional
	public Iterable<Specialoffer> findAll() {
		return soDao.findAll();
	}
	
	@Override
	@Transactional
	public Iterable<Specialoffer> findByStartDate(LocalDate startdate) {
		return soDao.findByStartDate(startdate);
	}
	
	@Override
	@Transactional
	public Iterable<Specialoffer> findByEndDate(LocalDate enddate) {
		return soDao.findByEndDate(enddate);
	}

	@Override
	@Transactional
	public Specialoffer findById(Integer id) {
		return soDao.findById(id);
	}
}
