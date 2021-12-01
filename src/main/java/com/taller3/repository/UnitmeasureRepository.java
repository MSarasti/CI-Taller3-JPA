package com.taller3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taller3.model.prod.*;

public interface UnitmeasureRepository extends CrudRepository<Unitmeasure, Integer> {
	List<Unitmeasure> findByName(String name);
}
