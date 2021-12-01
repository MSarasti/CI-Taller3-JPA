package com.taller3.service.interfaces;

import com.taller3.model.sales.Specialoffer;

public interface SpecialofferService {
	public Specialoffer saveSpecialOffer(Specialoffer s) throws Exception;
	public Specialoffer searchSpecialOffer(Integer sId);
	public Specialoffer updateSpecialOffer(Integer sId, Specialoffer s) throws Exception;
	public void deleteSpecialOffer(Integer sId);
}
