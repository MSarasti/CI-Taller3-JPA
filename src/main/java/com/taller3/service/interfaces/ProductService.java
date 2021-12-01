package com.taller3.service.interfaces;

import com.taller3.model.prod.*;

public interface ProductService {
	public Product saveProduct(Product p, Integer pCatId, Integer pSubId, Integer um1Id, Integer um2Id);
	public Product searchProduct(Integer pId);
	public Product updateProduct(Integer pId, Product p);
	public void deleteProduct(Integer pId);
	public Productcategory saveProductCategory(Productcategory pc);
	public Productcategory searchProductCategory(Integer pcId);
	public Productcategory updateProductCategory(Integer pcId, Productcategory pc);
	public void deleteProductCategory(Integer pcId);
	public Productsubcategory saveProductSubcategory(Productsubcategory psc);
	public Productsubcategory searchProductSubcategory(Integer pscId);
	public Productsubcategory updateProductSubcategory(Integer pscId, Productsubcategory psc);
	public void deleteProductSubcategory(Integer pscId);
}
