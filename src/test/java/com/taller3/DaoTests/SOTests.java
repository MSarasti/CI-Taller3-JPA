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
public class SOTests {
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
	}
	
	@Nested
	@DisplayName("Add special order tests")
	class addSOTests{
		
		@Test
		@DisplayName("Adds a special order correctly")
		public void testAddSOCorrectly() {
			so = new Specialoffer();
			so.setStartdate(LocalDate.now());
			so.setEnddate(LocalDate.now().plusWeeks(3));
			so.setCategory("Category");
			so.setDiscountpct(BigDecimal.valueOf(0.5));
			soDao.save(so);
			so.setSpecialofferid(soDao.findAll().get(0).getSpecialofferid());
			assertEquals(1, soDao.findAll().size());
		}
	}

	@Nested
	@DisplayName("Edit special order tests")
	class editSOTests{
		@Test
		@DisplayName("Edit an existing special offer's category")
		public void testEditSOCategory() {
			so = soDao.findById(1);
			so.setCategory("New Category");
			soDao.update(so);
			assertEquals(so.getCategory(), soDao.findById(1).getCategory());
		}
	}
	@Nested
	@DisplayName("Find product tests")
	class findSOTests{
		@Test
		@DisplayName("Find an existing special offer")
		public void testFindSO() {
			Specialoffer found = soDao.findById(1);
			assertEquals("0.50", found.getDiscountpct().toString());
		}
		
		@Test
		@DisplayName("Find all products")
		public void testFindAllSO() {
			List<Specialoffer> list = soDao.findAll();
			assertEquals(3, list.size());
		}
		
		@Test
		@DisplayName("Find special offers by start date")
		public void testFind() {
			Specialoffer so1 = new Specialoffer();
			so1.setCategory("Cat1");
			so1.setStartdate(LocalDate.now());
			Specialoffer so2 = new Specialoffer();
			so2.setCategory("Cat1");
			so2.setStartdate(LocalDate.now().plusDays(5));
			soDao.save(so1);
			soDao.save(so2);
			List<Specialoffer> list = soDao.findByStartDate(LocalDate.now());
			assertEquals(2, list.size());
		}
	}
}
