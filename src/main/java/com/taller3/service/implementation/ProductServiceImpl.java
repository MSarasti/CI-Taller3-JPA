package com.taller3.service.implementation;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller3.dao.implementation.ProductDaoImpl;
import com.taller3.model.prod.*;
import com.taller3.repository.*;
import com.taller3.service.interfaces.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	public ProductDaoImpl pDao;
	@Autowired
	public ProductCategoryRepository proCatRep;
	@Autowired
	public ProductSubcategoryRepository proSubRep;
	@Autowired
	public UnitmeasureRepository umRep;

	public ProductServiceImpl(ProductDaoImpl pDao, ProductCategoryRepository proCatRep,
			ProductSubcategoryRepository proSubRep, UnitmeasureRepository umRep) {
		this.pDao = pDao;
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
		pDao.save(p);

		return p;
	}

	@Override
	public Product searchProduct(Integer pId) {
		return pDao.findById(pId);
	}

	@Override
	public Product updateProduct(Integer pId, Product p) {
		Product toChange = pDao.findById(pId);
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
		pDao.update(toChange);
		return toChange;
	}

	@Override
	public void deleteProduct(Integer pId) {
		pDao.delete(pDao.findById(pId));
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
	
	public Product findById(Integer id){
		return pDao.findById(id);
	}
	
	public Product findByProductNumber(String productnumber) {
		return pDao.findByProductNumber(productnumber);
	}
	
	public Iterable<Product> findByStyle(String style) {
		return pDao.findByStyle(style);
	}
	
	public Iterable<Object[]> findByDateRange(LocalDate sellstartdate, LocalDate sellenddate) {
		return pDao.findByDateRange(sellstartdate, sellenddate);
	}
	
	public Iterable<String> findAllProductnumbers() {
		return pDao.findAllProductnumbers();
	}
	
	public Iterable<Product> findAllProducts() {
		return pDao.findAll();
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
