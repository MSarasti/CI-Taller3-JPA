package com.taller3.service.interfaces;

import com.taller3.model.prod.Productcategory;

public interface ProductcategoryService {
	public Productcategory saveProductCategory(Productcategory pc);
	public Productcategory searchProductCategory(Integer pcId);
	public Productcategory updateProductCategory(Integer pcId, Productcategory pc);
	public void deleteProductCategory(Integer pcId);
}
