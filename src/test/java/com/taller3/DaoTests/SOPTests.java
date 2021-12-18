package com.taller3.DaoTests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;

import com.taller3.*;
import com.taller3.dao.implementation.*;
import com.taller3.model.prod.*;
import com.taller3.model.sales.*;
import com.taller3.repository.*;

@SpringBootTest
@ContextConfiguration(classes = Taller3MsApplication.class)
@ExtendWith(SpringExtension.class)
public class SOPTests {
	@Autowired
	public SpecialOfferProductDaoImpl sopDao;
	@Autowired
	public SpecialOfferDaoImpl soDao;
	
	@Autowired
	public ProductDaoImpl prodDao;
	
	@Autowired
	public ProductCategoryRepository pCatRep;
	
	@Autowired
	public ProductSubcategoryRepository pSubRep;
	
	@Autowired
	public UnitmeasureRepository umRep;
	
	public Unitmeasure unit1;
	
	public Unitmeasure unit2;
	
	public Productcategory prodCat;
	
	public Productsubcategory prodSub;
	
	public Product prod;
	
	public Specialoffer so;
	
	public Specialofferproduct sop;
	
	@BeforeEach
	public void setUp1() {
		prod = new Product();
		prodCat = new Productcategory();
		prodSub = new Productsubcategory();
		unit1 = new Unitmeasure();
		unit2 = new Unitmeasure();
		
		unit1.setModifieddate(new Timestamp(System.currentTimeMillis()));
		unit1.setName("Name Unit 1");
		
		unit2.setModifieddate(new Timestamp(System.currentTimeMillis()));
		unit2.setName("Name Unit 2");
		
		unit1 = umRep.save(unit1);
		unit2 = umRep.save(unit2);
		
		prodCat.setName("Name Category");
		prodCat.setModifieddate(new Timestamp(System.currentTimeMillis()));
		
		prodCat = pCatRep.save(prodCat);
		
		prodSub.setName("Name Subcategory");
		prodSub.setModifieddate(new Timestamp(System.currentTimeMillis()));
		
		prodSub = pSubRep.save(prodSub);
		
		prod.setName("Name Product");
		prod.setColor("Blue");
		prod.setSize("Small");
		
		prodCat.addProductsubcategory(prodSub);
		prodSub.setProductcategory(prodCat);
		prod.setProductsubcategory(prodSub);
		
		prodDao.save(prod);
		
		so = new Specialoffer();
		so.setStartdate(LocalDate.now());
		so.setEnddate(LocalDate.now().plusWeeks(3));
		so.setCategory("Category");
		so.setDiscountpct(BigDecimal.valueOf(0.5));
		soDao.save(so);
		
		sop = new Specialofferproduct();
		SpecialofferproductPK pk = new SpecialofferproductPK();
		pk.setProductid(1);
		pk.setSpecialofferid(1);
		sop.setId(pk);
		sop.setModifieddate(LocalDate.now());
		sop.setSpecialoffer(so);
	}
	
	@Nested
	@DisplayName("Add special order tests")
	class addSOPTests{
		
		@Test
		@DisplayName("Adds a special order product correctly")
		public void testAddSOPCorrectly() {
			sopDao.save(sop);
			assertEquals(1, sopDao.findAll().size());
		}
	}

	@Nested
	@DisplayName("Edit special order product tests")
	class editSOPTests{
		@Test
		@DisplayName("Edit an existing special offer product's modified date")
		public void testEditSOPCategory() {
			SpecialofferproductPK pk = new SpecialofferproductPK();
			pk.setProductid(1);
			pk.setSpecialofferid(1);
			Specialofferproduct sop1 = sopDao.findById(pk);
			sop1.setModifieddate(LocalDate.now().plusDays(5));
			sopDao.update(sop1);
			assertEquals(LocalDate.now().plusDays(5), sopDao.findById(pk).getModifieddate());
		}
	}
	@Nested
	@DisplayName("Find special offer product tests")
	class findSOPTests{
		@Test
		@DisplayName("Find an existing special offer product")
		public void testFindSO() {
			SpecialofferproductPK pk = new SpecialofferproductPK();
			pk.setProductid(1);
			pk.setSpecialofferid(1);
			Specialofferproduct found = sopDao.findById(pk);
			assertEquals(1, found.getSpecialoffer().getSpecialofferid());
		}
		
		@Test
		@DisplayName("Find all special offer products")
		public void testFindAllSO() {
			List<Specialofferproduct> list = sopDao.findAll();
			assertEquals(1, list.size());
		}
	}
}
