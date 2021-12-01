package com.taller1.UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.taller3.model.prod.*;
import com.taller3.model.sales.*;
import com.taller3.repository.*;
import com.taller3.service.implementation.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SpecialOfferProductTests {/*
	@Mock
	public ProductRepository pRep;
	@Mock
	public SpecialofferRepository soRep;
	@Mock
	public SpecialofferproductRepository sopRep;
	
	@InjectMocks
	public SpecialOfferProductServiceImpl sopServ;
	
	@InjectMocks
	public ProductServiceImpl prodServ;
	
	@InjectMocks
	public SpecialOfferServiceImpl soServ;
	
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
			try {
				Mockito.when(sopServ.saveSpecialOfferProduct(null, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sopRep);
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with no product, throws exception")
		public void addSOPWithNoProduct() {
			try {
				Mockito.when(sopServ.saveSpecialOfferProduct(sop, 0, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sopRep);
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with no Specialoffer, throws exception")
		public void addSOPWithNoSO() {
			try {
				Mockito.when(sopServ.saveSpecialOfferProduct(sop, 1, 0)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sopRep);
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with null modified date, throws exception")
		public void addSOPWithNullModDate() {
			sop.setModifieddate(null);
			try {
				Mockito.when(sopServ.saveSpecialOfferProduct(sop, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sopRep);
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with null modified date, throws exception")
		public void addSOPWithBeforeModDate() {
			sop.setModifieddate(new Timestamp(System.currentTimeMillis()-100000));
			try {
				Mockito.when(sopServ.saveSpecialOfferProduct(sop, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sopRep);
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct with null modified date, throws exception")
		public void addSOPWithAfterModDate() {
			sop.setModifieddate(new Timestamp(System.currentTimeMillis()+100000));
			try {
				Mockito.when(sopServ.saveSpecialOfferProduct(sop, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sopRep);
		}
		
		@Test
		@DisplayName("Add SpecialOfferProduct correctly")
		public void addSOPCorrectly() {
			try {
				lenient().when(sopServ.saveSpecialOfferProduct(sop, 1, 1)).thenReturn(sop);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoMoreInteractions(sopRep);
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
			try {
				Mockito.when(sopServ.updateSpecialOfferProduct(sopKey, null)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sopRep).findById(sopKey);
		}
		
		@Test
		@DisplayName("Edit an existing SpecialOfferProduct's modified date to null, throws exception")
		public void editSOPWithNullModDate() {
			sop.setModifieddate(null);
			try {
				Mockito.when(sopServ.updateSpecialOfferProduct(sopKey, sop)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sopRep).findById(sopKey);
		}
		
		@Test
		@DisplayName("Edit an existing SpecialOfferProduct's modified date to null, throws exception")
		public void editSOPWithBeforeModDate() {
			sop.setModifieddate(new Timestamp(System.currentTimeMillis()-100000));
			try {
				Mockito.when(sopServ.updateSpecialOfferProduct(sopKey, sop)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sopRep).findById(sopKey);
		}
		
		@Test
		@DisplayName("Edit an existing SpecialOfferProduct's modified date to null, throws exception")
		public void editSOPWithAfterModDate() {
			sop.setModifieddate(new Timestamp(System.currentTimeMillis()+100000));
			try {
				Mockito.when(sopServ.updateSpecialOfferProduct(sopKey, sop)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sopRep).findById(sopKey);
		}
		
		@Test
		@DisplayName("Edit an existing SpecialOfferProduct correctly")
		public void editSOPCorrectly() {
			sop.setRowguid(2);
			try {
				Mockito.when(sopServ.updateSpecialOfferProduct(sopKey, sop)).thenReturn(sop);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sopRep).findById(sopKey);
		}
	}
	*/
}