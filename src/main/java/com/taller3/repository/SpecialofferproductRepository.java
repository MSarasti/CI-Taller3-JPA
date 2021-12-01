package com.taller3.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taller3.model.sales.Specialoffer;
import com.taller3.model.sales.Specialofferproduct;
import com.taller3.model.sales.SpecialofferproductPK;

public interface SpecialofferproductRepository extends CrudRepository<Specialofferproduct, SpecialofferproductPK> {
	Optional<Specialofferproduct> findBySpecialoffer(Specialoffer specialoffer);
}
