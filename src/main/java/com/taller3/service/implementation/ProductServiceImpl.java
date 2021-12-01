package com.taller3.service.implementation;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller3.model.prod.*;
import com.taller3.repository.*;
import com.taller3.service.interfaces.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	public ProductRepository proRep;
	@Autowired
	public ProductCategoryRepository proCatRep;
	@Autowired
	public ProductSubcategoryRepository proSubRep;
	@Autowired
	public UnitmeasureRepository umRep;

	public ProductServiceImpl(ProductRepository proRep, ProductCategoryRepository proCatRep,
			ProductSubcategoryRepository proSubRep, UnitmeasureRepository umRep) {
		this.proRep = proRep;
		this.proCatRep = proCatRep;
		this.proSubRep = proSubRep;
		this.umRep = umRep;
	}

	@Override
	public Product saveProduct(Product p, Integer pCatId, Integer pSubId, Integer um1Id, Integer um2Id) /* throws Exception */ {
		Optional<Productcategory> opCat = proCatRep.findById(pCatId);
		Optional<Productsubcategory> opSub = proSubRep.findById(pSubId);
		Optional<Unitmeasure> opUm1 = umRep.findById(um1Id);
		Optional<Unitmeasure> opUm2 = umRep.findById(um2Id);
		Productcategory pcat = opCat.get();
		Productsubcategory psub = opSub.get();
		Unitmeasure um1 = opUm1.get();
		Unitmeasure um2 = opUm2.get();
		psub.setProductcategory(pcat);
		p.setProductsubcategory(psub);
		p.setUnitmeasure1(um1);
		p.setUnitmeasure2(um2);
		proCatRep.save(pcat);
		proSubRep.save(psub);
		umRep.save(um1);
		umRep.save(um2);
		proRep.save(p);

		return p;
	}

	@Override
	public Product searchProduct(Integer pId) {
		Optional<Product> opProd = proRep.findById(pId);
		return (opProd.isPresent()) ? opProd.get() : null;
	}

	@Override
	public Product updateProduct(Integer pId, Product p) {
		Product toChange = proRep.findById(pId).get();
		toChange.setBillofmaterials1(p.getBillofmaterials1());
		toChange.setBillofmaterials2(p.getBillofmaterials2());
		toChange.setClass_(p.getClass_());
		toChange.setColor(p.getColor());
		toChange.setDaystomanufacture(p.getDaystomanufacture());
		toChange.setDiscontinueddate(p.getDiscontinueddate());
		toChange.setFinishedgoodsflag(p.getFinishedgoodsflag());
		toChange.setListprice(p.getListprice());
		toChange.setMakeflag(p.getMakeflag());
		toChange.setModifieddate(p.getModifieddate());
		toChange.setName(p.getName());
		toChange.setProductcosthistories(p.getProductcosthistories());
		toChange.setProductdocuments(p.getProductdocuments());
		toChange.setProductinventories(p.getProductinventories());
		toChange.setProductline(p.getProductline());
		toChange.setProductlistpricehistories(p.getProductlistpricehistories());
		toChange.setProductmodel(p.getProductmodel());
		toChange.setProductnumber(p.getProductnumber());
		toChange.setProductproductphotos(p.getProductproductphotos());
		toChange.setProductreviews(p.getProductreviews());
		toChange.setProductsubcategory(p.getProductsubcategory());
		toChange.setReorderpoint(p.getReorderpoint());
		toChange.setRowguid(p.getRowguid());
		toChange.setSafetystocklevel(p.getSafetystocklevel());
		toChange.setSellenddate(p.getSellenddate());
		toChange.setSellstartdate(p.getSellstartdate());
		toChange.setSize(p.getSize());
		toChange.setStandardcost(p.getStandardcost());
		toChange.setStyle(p.getStyle());
		toChange.setTransactionhistories(p.getTransactionhistories());
		toChange.setUnitmeasure1(p.getUnitmeasure1());
		toChange.setUnitmeasure2(p.getUnitmeasure2());
		toChange.setWeight(p.getWeight());
		toChange.setWorkorders(p.getWorkorders());
		proRep.save(toChange);
		return toChange;
	}

	@Override
	public void deleteProduct(Integer pId) {
		proRep.delete(proRep.findById(pId).get());
	}

	@Override
	public Productcategory saveProductCategory(Productcategory pc) {
		proCatRep.save(pc);
		return pc;
	}

	@Override
	public Productcategory searchProductCategory(Integer pcId) {
		Optional<Productcategory> opProd = proCatRep.findById(pcId);
		return (opProd.isPresent()) ? opProd.get() : null;
	}

	@Override
	public Productcategory updateProductCategory(Integer pcId, Productcategory pc) {
		return pc;
	}

	@Override
	public void deleteProductCategory(Integer pcId) {
		proCatRep.delete(proCatRep.findById(pcId).get());
	}

	@Override
	public Productsubcategory saveProductSubcategory(Productsubcategory psc) {
		proSubRep.save(psc);
		return psc;
	}

	@Override
	public Productsubcategory searchProductSubcategory(Integer pscId) {
		Optional<Productsubcategory> opProd = proSubRep.findById(pscId);
		return (opProd.isPresent()) ? opProd.get() : null;
	}

	@Override
	public Productsubcategory updateProductSubcategory(Integer pscId, Productsubcategory psc) {
		return psc;
	}

	@Override
	public void deleteProductSubcategory(Integer pscId) {
		proSubRep.delete(proSubRep.findById(pscId).get());
	}

	public Unitmeasure saveUnitmeasure(Unitmeasure um) {
		umRep.save(um);
		return um;
	}

	public Unitmeasure searchUnitmeasure(Integer umId) {
		Optional<Unitmeasure> opUm = umRep.findById(umId);
		return (opUm.isPresent()) ? opUm.get() : null;
	}

	public Unitmeasure updateUnitmeasure(String umId, Unitmeasure um) {
		return um;
	}

	public void deleteUnitmeasure(Integer umId) {
		umRep.delete(umRep.findById(umId).get());
	}
	
	public Optional<Product> findById(Integer id){
		return proRep.findById(id);
	}
	
	public Iterable<Product> findAllProducts() {
		return proRep.findAll();
	}

	public Iterable<Productcategory> findAllCategories() {
		return proCatRep.findAll();
	}

	public Iterable<Productsubcategory> findAllSubcategories() {
		return proSubRep.findAll();
	}

	public Iterable<Unitmeasure> findAllUnits() {
		return umRep.findAll();
	}
}
