package com.taller1.UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.taller3.model.prod.*;
import com.taller3.model.sales.*;
import com.taller3.repository.*;
import com.taller3.service.implementation.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SalesOrderDetailTests {/*
	@Mock
	public ProductRepository pRep;
	
	@Mock
	public ProductCategoryRepository pCRep;
	
	@Mock
	public ProductSubcategoryRepository pSRep;
	
	@Mock
	public SpecialofferRepository soRep;
	
	@Mock
	public SalesorderdetailRepository sodRep;
	
	@InjectMocks
	public SalesOrderDetailServiceImpl sodServ;
	
	@InjectMocks
	public ProductServiceImpl prodServ;
	
	@InjectMocks
	public SpecialOfferServiceImpl soServ;
	
	public Salesorderdetail sod;
	
	public SalesorderdetailPK sodPK;
	
	public Product p;
	
	public Productcategory pCat;
	
	public Productsubcategory pSub;
	
	public Specialoffer so;
	
	@BeforeEach
	public void setUp1() {
		sod = new Salesorderdetail();
		sodPK = new SalesorderdetailPK();
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
		sod.setCarriertrackingnumber("Carrier");
		sod.setUnitprice(BigDecimal.valueOf(10));
		sod.setUnitpricediscount(BigDecimal.valueOf(10));
		sodPK.setSalesorderdetailid(1);
		sodPK.setSalesorderid(1);
		sod.setId(sodPK);
	}
	
	@Test
	public void test() {
		assertTrue(true);
	}
	
	@Nested
	@DisplayName("Add SalesOrderDetail Tests")
	class addSODTests {
		@Test
		@DisplayName("Add a null SalesOrderDetail, throws exception")
		public void testAddSODNull() {
			try {
				Mockito.when(sodServ.saveSalesOrderDetail(null, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sodRep);
		}
		
		@Test
		@DisplayName("Add a SalesOrderDetail with no product, throws exception")
		public void testAddSODWithNoProduct() {
			try {
				Mockito.when(sodServ.saveSalesOrderDetail(sod, 0, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sodRep);
		}
		
		@Test
		@DisplayName("Add a SalesOrderDetail with no SpecialOffer, throws exception")
		public void testAddSODWithNoSO() {
			try {
				Mockito.when(sodServ.saveSalesOrderDetail(sod, 1, 0)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sodRep);
		}
		
		@Test
		@DisplayName("Add a SalesOrderDetail with a null unitary price, throws exception")
		public void testAddSODWithNullUP() {
			sod.setUnitprice(null);
			try {
				Mockito.when(sodServ.saveSalesOrderDetail(sod, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sodRep);
		}
		
		@Test
		@DisplayName("Add a SalesOrderDetail with a negative unitary price, throws exception")
		public void testAddSODWithUPLessThan0() {
			sod.setUnitprice(BigDecimal.valueOf(-1));
			try {
				Mockito.when(sodServ.saveSalesOrderDetail(sod, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sodRep);
		}
		
		@Test
		@DisplayName("Add a SalesOrderDetail with a unitary price of 0, throws exception")
		public void testAddSODWithUPAs0() {
			sod.setUnitprice(BigDecimal.valueOf(0));
			try {
				Mockito.when(sodServ.saveSalesOrderDetail(sod, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sodRep);
		}
		
		@Test
		@DisplayName("Add a SalesOrderDetail with a null unitary price discount, throws exception")
		public void testAddSODWithNullUPD() {
			sod.setUnitpricediscount(null);
			try {
				Mockito.when(sodServ.saveSalesOrderDetail(sod, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sodRep);
		}
		
		@Test
		@DisplayName("Add a SalesOrderDetail with a negative unitary price discount, throws exception")
		public void testAddSODWithUPDLessThan0() {
			sod.setUnitpricediscount(BigDecimal.valueOf(-1));
			try {
				Mockito.when(sodServ.saveSalesOrderDetail(sod, 1, 1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sodRep);
		}

		@Test
		@DisplayName("Add a SalesOrderDetail correctly")
		public void testAddSOPCorrectly() {
			try {
				lenient().when(sodServ.saveSalesOrderDetail(sod, 1, 1)).thenReturn(sod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(sodRep);
		}
	}
	
	@Nested
	@DisplayName("Edit SalesOrderDetail Tests")
	class editSODTests {
		@Test
		@DisplayName("Edit an existing SOD to null, throws exception")
		public void testEditSODToNull() {
			try {
				Mockito.when(sodServ.updateSalesOrderDetail(sodPK, null)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sodRep).findById(sodPK);
		}
		
		@Test
		@DisplayName("Edit an existing SOD's unit price to null, throws exception")
		public void testEditSODWithNullUP() {
			sod.setUnitprice(null);
			try {
				Mockito.when(sodServ.updateSalesOrderDetail(sodPK, sod)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sodRep).findById(sodPK);
		}
		
		@Test
		@DisplayName("Edit an existing SOD's unit price to a negative number, throws exception")
		public void testEditSODWithNegativeUP() {
			sod.setUnitprice(BigDecimal.valueOf(-1));
			try {
				Mockito.when(sodServ.updateSalesOrderDetail(sodPK, sod)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sodRep).findById(sodPK);
		}
		
		@Test
		@DisplayName("Edit an existing SOD's unit price to 0, throws exception")
		public void testEditSODWithUPAs0() {
			sod.setUnitprice(BigDecimal.valueOf(0));
			try {
				Mockito.when(sodServ.updateSalesOrderDetail(sodPK, sod)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sodRep).findById(sodPK);
		}
		
		@Test
		@DisplayName("Edit an existing SOD's unit price discount to null, throws exception")
		public void testEditSODWithNullUPD() {
			sod.setUnitpricediscount(null);
			try {
				Mockito.when(sodServ.updateSalesOrderDetail(sodPK, sod)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sodRep).findById(sodPK);
		}
		
		@Test
		@DisplayName("Edit an existing SOD's unit price discount to a negative number, throws exception")
		public void testEditSODWithNegativeUPD() {
			sod.setUnitpricediscount(BigDecimal.valueOf(-1));
			try {
				Mockito.when(sodServ.updateSalesOrderDetail(sodPK, sod)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sodRep).findById(sodPK);
		}
		
		@Test
		@DisplayName("Edit an existing SOD correctly")
		public void testEditSODCorrectly() {
			sod.setRowguid(5);
			try {
				lenient().when(sodServ.updateSalesOrderDetail(sodPK, sod)).thenReturn(sod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(sodRep).findById(sodPK);
		}
	}*/
}
