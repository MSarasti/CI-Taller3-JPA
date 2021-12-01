package com.taller3.service.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller3.model.sales.Specialoffer;
import com.taller3.repository.SpecialofferRepository;
import com.taller3.service.interfaces.SpecialofferService;

@Service
public class SpecialOfferServiceImpl implements SpecialofferService {
	
	@Autowired
	private SpecialofferRepository soRepo;
	
	public SpecialOfferServiceImpl(SpecialofferRepository soRepo) {
		this.soRepo = soRepo;
	}

	@Override
	public Specialoffer saveSpecialOffer(Specialoffer s) {
		soRepo.save(s);
		return s;
	}

	@Override
	public Specialoffer searchSpecialOffer(Integer sId) {
		Optional<Specialoffer> opSO = soRepo.findById(sId);
		return (opSO.isEmpty()) ? null : opSO.get();
	}

	@Override
	public Specialoffer updateSpecialOffer(Integer sId, Specialoffer s) {
		Specialoffer toChange = soRepo.findById(sId).get();
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
		soRepo.save(toChange);
		return s;
	}

	@Override
	public void deleteSpecialOffer(Integer sId) {
		soRepo.delete(soRepo.findById(sId).get());
	}

	public Iterable<Specialoffer> findAll() {
		return soRepo.findAll();
	}

	public Optional<Specialoffer> findById(Integer id) {
		return soRepo.findById(id);
	}
}
