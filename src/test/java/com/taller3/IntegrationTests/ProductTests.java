package com.taller3.IntegrationTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.taller3.model.prod.*;
import com.taller3.repository.*;
import com.taller3.service.implementation.*;
import com.taller3.service.interfaces.ProductService;

@SpringBootTest
public class ProductTests {
	@Autowired
	public ProductService prodServ;
	
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
		prodCat.setProductcategoryid(1);
		prodCat.setName("Name Category");
		prodCat.setModifieddate(new Timestamp(System.currentTimeMillis()));
		prodCat.setRowguid(1);
		prodSub.setProductsubcategoryid(1);
		prodSub.setName("Name Subcategory");
		prodSub.setModifieddate(new Timestamp(System.currentTimeMillis()));
		prodSub.setRowguid(1);
		prod.setProductid(1);
		prod.setName("Name Product");
		prod.setColor("Blue");
		prod.setSize("Small");
		prodServ.saveProductCategory(prodCat);
		prodServ.saveProductSubcategory(prodSub);
	}
	
	@Test
	public void test() {
		assertTrue(true);
	}
	
	@Nested
	@DisplayName("Add product tests")
	class addProductTests{
		@Test
		@DisplayName("Add null product, throws exception")
		public void testAddNullProduct() {
			assertThrows(Exception.class, () -> prodServ.saveProduct(null, null, null, null, null));
		}
		
		@Test
		@DisplayName("Add a product without a category, throws exception")
		public void testAddProductWithoutCategory() {
			assertThrows(Exception.class, () -> prodServ.saveProduct(prod, null, prodSub.getProductsubcategoryid(), unit1.getUnitmeasurecode(), unit2.getUnitmeasurecode()));
		}
		
		@Test
		@DisplayName("Product number is less than 1, throws exception")
		public void testAddProductWithNumberLessThan1() {
			prod.setProductnumber("0");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(LocalDate.now().minusDays(10));
			prod.setSellenddate(LocalDate.now());
			assertThrows(Exception.class, () -> prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1.getUnitmeasurecode(), unit2.getUnitmeasurecode()));
		}
		
		@Test
		@DisplayName("Sell end date is before sell start date, throws exception")
		public void testAddProductWithEndDateBeforeStartDate() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(LocalDate.now().plusDays(10));
			prod.setSellenddate(LocalDate.now());
			assertThrows(Exception.class, () -> prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1.getUnitmeasurecode(), unit2.getUnitmeasurecode()));
		}
		
		@Test
		@DisplayName("Adds a product correctly")
		public void testAddProductCorrectly() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(LocalDate.now().minusDays(10));
			prod.setSellenddate(LocalDate.now().plusDays(10));
			assertDoesNotThrow(() -> prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1.getUnitmeasurecode(), unit2.getUnitmeasurecode()));
			assertEquals(prod.getProductid(), prodServ.searchProduct(1).getProductid());
			assertEquals(prod.getSellstartdate(), prodServ.searchProduct(1).getSellstartdate());
		}
	}

	@Nested
	@DisplayName("Edit product tests")
	class editProductTests{
		public Product prod2;
		@BeforeEach
		public void setUpEdit() {
			prod2 = new Product();
			prod2.setProductid(1);
			prod2.setName("New Product");
			prod2.setColor("Black");
			prod2.setSize("Large");
			
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(LocalDate.now().minusDays(10));
			prod.setSellenddate(LocalDate.now());
			try {
				prodServ.saveProduct(prod, 1, 1, unit1.getUnitmeasurecode(), unit2.getUnitmeasurecode());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		@DisplayName("Edit an existing product to null, throws exception")
		public void testEditProductToNull() {
			assertThrows(Exception.class, () -> prodServ.updateProduct(1, null));
		}
		
		@Test
		@DisplayName("Edit an existing product's name")
		public void testEditProductName() {
			Product toChange = prod;
			toChange.setName("New name");
			assertDoesNotThrow(() -> prodServ.updateProduct(1, toChange));
			assertEquals(toChange.getName(), prodServ.searchProduct(1).getName());
		}
		
		@Test
		@DisplayName("Edit an existing product's color")
		public void testEditProductColor() {
			Product toChange = prod;
			toChange.setColor("Red");
			assertDoesNotThrow(() -> prodServ.updateProduct(1, toChange));
			assertEquals(toChange.getColor(), prodServ.searchProduct(1).getColor());
		}
		
		@Test
		@DisplayName("Edit an existing product's size")
		public void testEditProductSize() {
			Product toChange = prod;
			toChange.setSize("Medium");
			assertDoesNotThrow(() -> prodServ.updateProduct(1, toChange));
			assertEquals(toChange.getSize(), prodServ.searchProduct(1).getSize());
		}
		
		@Test
		@DisplayName("Edit an existing product's number to a number that's greater than 0")
		public void testEditProductNumberMoreThan1() {
			Product toChange = prod;
			toChange.setProductnumber("2");
			assertDoesNotThrow(() -> prodServ.updateProduct(1, toChange));
			assertEquals(toChange.getProductnumber(), prodServ.searchProduct(1).getProductnumber());
		}
		
		@Test
		@DisplayName("Edit an existing product's sell start and end date")
		public void testEditProductSellDatesCorrect() {
			Product toChange = prod;
			toChange.setSellenddate(LocalDate.now().plusDays(15));
			toChange.setSellstartdate(LocalDate.now().minusDays(3));
			assertDoesNotThrow(() -> prodServ.updateProduct(1, toChange));
			assertEquals(toChange.getSellstartdate(), prodServ.searchProduct(1).getSellstartdate());
		}
		
	}
}
