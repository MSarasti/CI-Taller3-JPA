package com.taller3.DaoTests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.taller3.Taller3MsApplication;
import com.taller3.dao.implementation.*;
import com.taller3.model.prod.*;

@SpringBootTest
@ContextConfiguration(classes = Taller3MsApplication.class)
@ExtendWith(SpringExtension.class)
public class ProductTests {
	@Autowired
	public ProductDaoImpl prodDao;
	
	public Unitmeasure unit1;
	
	public Unitmeasure unit2;
	
	public Productcategory prodCat;
	
	public Productsubcategory prodSub;
	
	public Product prod;
	
	@BeforeEach
	public void setUp1() {
		prod = new Product();
		prodCat = new Productcategory();
		prodSub = new Productsubcategory();
		unit1 = new Unitmeasure();
		unit2 = new Unitmeasure();
		
		unit1.setUnitmeasurecode(1);
		unit1.setModifieddate(new Timestamp(System.currentTimeMillis()));
		unit1.setName("Name Unit 1");
		
		unit2.setUnitmeasurecode(2);
		unit2.setModifieddate(new Timestamp(System.currentTimeMillis()));
		unit2.setName("Name Unit 2");
		
		prodCat.setName("Name Category");
		prodCat.setModifieddate(new Timestamp(System.currentTimeMillis()));
		
		prodSub.setName("Name Subcategory");
		prodSub.setModifieddate(new Timestamp(System.currentTimeMillis()));
		
		prod.setName("Name Product");
		prod.setColor("Blue");
		prod.setSize("Small");
		
		unit1.addProducts1(prod);
		unit2.addProducts2(prod);
		prodCat.addProductsubcategory(prodSub);
		prodSub.setProductcategory(prodCat);
		prod.setProductsubcategory(prodSub);
	}
	
	@Nested
	@DisplayName("Add product tests")
	class addProductTests{
		
		@Test
		@DisplayName("Adds a product correctly")
		public void testAddProductCorrectly() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(LocalDate.now().minusDays(10));
			prod.setSellenddate(LocalDate.now().plusDays(10));
			prodDao.save(prod);
			assertNotNull(prodDao.findById(prod.getProductid()));
		}
	}

	@Nested
	@DisplayName("Edit product tests")
	class editProductTests{
		@BeforeEach
		public void setUp2() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(LocalDate.now().minusDays(10));
			prod.setSellenddate(LocalDate.now().plusDays(10));
		}
		
		@Test
		@DisplayName("Edit an existing product's name")
		public void testEditProductName() {
			Product toChange = prod;
			toChange.setName("New name");
			prodDao.update(toChange);
			assertEquals(toChange.getName(), prodDao.findById(prod.getProductid()).getName());
		}
		
		@Test
		@DisplayName("Edit an existing product's number to a number that's greater than 0")
		public void testEditProductNumberMoreThan1() {
			Product toChange = prod;
			toChange.setProductnumber("2");
			prodDao.update(toChange);
			assertEquals(toChange.getProductnumber(), prodDao.findById(prod.getProductid()).getProductnumber());
		}
		
		@Test
		@DisplayName("Edit an existing product's sell start and end date")
		public void testEditProductSellDatesCorrect() {
			Product toChange = prod;
			toChange.setSellstartdate(LocalDate.now());
			toChange.setSellenddate(LocalDate.now().plusDays(7));
			prodDao.update(toChange);
			assertEquals(toChange.getSellstartdate(), prodDao.findById(prod.getProductid()).getSellstartdate());
			assertEquals(toChange.getSellenddate(), prodDao.findById(prod.getProductid()).getSellenddate());
		}
		
	}
}
