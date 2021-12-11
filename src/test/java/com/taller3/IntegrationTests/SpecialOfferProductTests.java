package com.taller3.IntegrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.taller3.model.prod.*;
import com.taller3.model.sales.*;
import com.taller3.service.interfaces.*;

@SpringBootTest
public class SpecialOfferProductTests {/*
	@Autowired
	public SpecialofferproductService sopServ;
	
	@Autowired
	public SpecialofferService soServ;
	
	@Autowired
	public ProductService prodServ;
	
	public Specialofferproduct sop;
	
	public SpecialofferproductPK sopKey;
	
	public Product p;
	
	public Productcategory pCat;
	
	public Productsubcategory pSub;
	
	public Specialoffer so;
	
	@BeforeEach
	public void setUp1() {
		sop = new Specialofferproduct();
		sopKey = new SpecialofferproductPK();
		p = new Product();
		pCat = new Productcategory();
		pSub = new Productsubcategory();
		Unitmeasure um = new Unitmeasure();
		so = new Specialoffer();
		p.setProductnumber("1");
		p.setDaystomanufacture(1);
		p.setSellstartdate(new Timestamp(System.currentTimeMillis()-100000));
		p.setSellenddate(new Timestamp(System.currentTimeMillis()));
		pCat.setProductcategoryid(1);
		pSub.setProductsubcategoryid(1);
		um.setUnitmeasurecode("1");
		so.setCategory("Category");
		so.setDiscountpct(BigDecimal.valueOf(10));
		so.setModifieddate(new Timestamp(System.currentTimeMillis()));
		try {
			prodServ.saveProductCategory(pCat);
			prodServ.saveProductSubcategory(pSub);
			prodServ.saveProduct(p, 1, 1, um);
			soServ.saveSpecialOffer(so);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sop.setModifieddate(new Timestamp(System.currentTimeMillis()));
		sop.setId(sopKey);
	}
	
	@Test
	public void test() {
		assertTrue(true);
	}
	
	@Nested
	@DisplayName("Add SpecialOfferProduct Tests")
	class addSOPTests {
		@Test
		@DisplayName("Add a null SpecialOfferProduct, throws exception")
		public void addSOPNull() {
			assertThrows(Exception.class, () -> sopServ.saveSpecialOfferProduct(null, 1, 1));
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with no product, throws exception")
		public void addSOPWithNoProduct() {
			assertThrows(Exception.class, () -> sopServ.saveSpecialOfferProduct(sop, 0, 1));
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with no Specialoffer, throws exception")
		public void addSOPWithNoSO() {
			assertThrows(Exception.class, () -> sopServ.saveSpecialOfferProduct(sop, 1, 0));
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with null modified date, throws exception")
		public void addSOPWithNullModDate() {
			sop.setModifieddate(null);
			assertThrows(Exception.class, () -> sopServ.saveSpecialOfferProduct(sop, 1, 1));
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with null modified date, throws exception")
		public void addSOPWithBeforeModDate() {
			sop.setModifieddate(new Timestamp(System.currentTimeMillis()-100000));
			assertThrows(Exception.class, () -> sopServ.saveSpecialOfferProduct(sop, 1, 1));
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with null modified date, throws exception")
		public void addSOPWithAfterModDate() {
			sop.setModifieddate(new Timestamp(System.currentTimeMillis()+100000));
			assertThrows(Exception.class, () -> sopServ.saveSpecialOfferProduct(sop, 1, 1));
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct correctly")
		public void addSOPCorrectly() {
			assertDoesNotThrow(() -> sopServ.saveSpecialOfferProduct(sop, 1, 1));
		}
	}
	
	@Nested
	@DisplayName("Edit SpecialOfferProduct Tests")
	class editSOPTests {
		@BeforeEach
		public void setUpKey() {
			sopKey.setProductid(1);
			sopKey.setSpecialofferid(1);
		}
		
		@Test
		@DisplayName("Edit an existing SpecialOfferProduct to null, throws exception")
		public void editSOPToNull() {
			assertThrows(Exception.class, () -> sopServ.updateSpecialOfferProduct(sopKey, null));
		}
		
		@Test
		@DisplayName("Edit an existing SpecialOfferProduct's modified date to null, throws exception")
		public void editSOPWithNullModDate() {
			sop.setModifieddate(null);
			assertThrows(Exception.class, () -> sopServ.updateSpecialOfferProduct(sopKey, sop));
		}
		
		@Test
		@DisplayName("Edit an existing SpecialOfferProduct's modified date to null, throws exception")
		public void editSOPWithBeforeModDate() {
			sop.setModifieddate(new Timestamp(System.currentTimeMillis()-100000));
			assertThrows(Exception.class, () -> sopServ.updateSpecialOfferProduct(sopKey, sop));
		}
		
		@Test
		@DisplayName("Edit an existing SpecialOfferProduct's modified date to null, throws exception")
		public void editSOPWithAfterModDate() {
			sop.setModifieddate(new Timestamp(System.currentTimeMillis()+100000));
			assertThrows(Exception.class, () -> sopServ.updateSpecialOfferProduct(sopKey, sop));
		}
		
		@Test
		@DisplayName("Edit an existing SpecialOfferProduct correctly")
		public void editSOPCorrectly() {
			sop.setRowguid(2);
			assertDoesNotThrow(() -> sopServ.updateSpecialOfferProduct(sopKey, sop));
			assertEquals(2, sopServ.searchSpecialOfferProduct(sopKey).getRowguid());
		}
	}*/
	
}