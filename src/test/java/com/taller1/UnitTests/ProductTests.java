package com.taller1.UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.taller3.model.prod.*;
import com.taller3.repository.*;
import com.taller3.service.implementation.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductTests {/*
	@Mock
	public ProductRepository prodRepository;
	
	@Mock
	public ProductCategoryRepository prodCatRepository;
	
	@Mock
	public ProductSubcategoryRepository prodSubRepository;
	
	@InjectMocks
	public ProductServiceImpl prodServ;
	
	public Unitmeasure unit1;
	
	public Productcategory prodCat;
	
	public Productsubcategory prodSub;
	
	public Product prod;
	
	@BeforeEach
	public void setUp1() {
		prod = new Product();
		prodCat = new Productcategory();
		prodSub = new Productsubcategory();
		unit1 = new Unitmeasure();
		unit1.setUnitmeasurecode(1);
		unit1.setModifieddate(new Timestamp(System.currentTimeMillis()));
		unit1.setName("Name Unit");
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
		prodServ.saveProductCategory(prodCat);
		prodServ.saveProductSubcategory(prodSub);
		//lenient().when(prodServ.saveProductCategory(prodCat)).thenReturn(prodCat);
		//lenient().when(prodServ.saveProductSubcategory(prodSub)).thenReturn(prodSub);
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
			try {
				Mockito.when(prodServ.saveProduct(null, null, null, null)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}

		@Test
		@DisplayName("Add a product without a category, throws exception")
		public void testAddProductWithoutCategory() {
			try {
				Mockito.when(prodServ.saveProduct(prod, null, prodSub.getProductsubcategoryid(), unit1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Add a product without a subcategory, throws exception")
		public void testAddProductWithoutSubCategory() {
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), null, unit1)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Add a product without a unit measure, throws exception")
		public void testAddProductWithoutUnitMeasure() {
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), null)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Product number is less than 1, throws exception")
		public void testAddProductWithNumberLessThan1() {
			prod.setProductnumber("0");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(new Timestamp(System.currentTimeMillis()-100));
			prod.setSellenddate(new Timestamp(System.currentTimeMillis()));
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1)).thenThrow(Exception.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Product number is a string that can't be parsed, throws exception")
		public void testAddProductWithProductNumberAsAString() {
			prod.setProductnumber("A");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(new Timestamp(System.currentTimeMillis()-100));
			prod.setSellenddate(new Timestamp(System.currentTimeMillis()));
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1)).thenThrow(Exception.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Days to manufacture is less than 1, throws exception")
		public void testAddProductWithDaysManufactureLessThan1() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(0);
			prod.setSellstartdate(new Timestamp(System.currentTimeMillis()-100));
			prod.setSellenddate(new Timestamp(System.currentTimeMillis()));
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1)).thenThrow(Exception.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Sell end date is before sell start date, throws exception")
		public void testAddProductWithEndDateBeforeStartDate() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(new Timestamp(System.currentTimeMillis()+100));
			prod.setSellenddate(new Timestamp(System.currentTimeMillis()));
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1)).thenThrow(Exception.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Sell end date and sell start date are null, throws exception")
		public void testAddProductWithNullSellDates() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(null);
			prod.setSellenddate(null);
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1)).thenThrow(Exception.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Sell start date is null, throws exception")
		public void testAddProductWithNullSellStartDate() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(null);
			prod.setSellenddate(new Timestamp(System.currentTimeMillis()+100));
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1)).thenThrow(Exception.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Sell end date is null, throws exception")
		public void testAddProductWithNullSellEndDate() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(new Timestamp(System.currentTimeMillis()-100));
			prod.setSellenddate(null);
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1)).thenThrow(Exception.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(prodRepository);
		}
		
		@Test
		@DisplayName("Adds one product correctly")
		public void testAddProductCorrectly() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(new Timestamp(System.currentTimeMillis()-100));
			prod.setSellenddate(new Timestamp(System.currentTimeMillis()));
			try {
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1)).thenReturn(prod);
			}catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoMoreInteractions(prodRepository);
		}
	}
	
	@Nested
	@DisplayName("Edit product tests")
	class editProductTests{
		@BeforeEach
		public void setUpEdit() {
			prod.setProductnumber("1");
			prod.setDaystomanufacture(1);
			prod.setSellstartdate(new Timestamp(System.currentTimeMillis()-100));
			prod.setSellenddate(new Timestamp(System.currentTimeMillis()));
			try {
				//System.out.println(prodCat.getProductcategoryid());
				//System.out.println(prodCatRepository.findById(0).get());
				Mockito.when(prodServ.saveProduct(prod, prodCat.getProductcategoryid(), prodSub.getProductsubcategoryid(), unit1)).thenReturn(prod);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		@DisplayName("Edit an existing product to null, throws exception")
		public void testEditProductToNull() {
			try {
				Mockito.when(prodServ.updateProduct(1, null)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing product's number to a number that's less than 1, throws exception")
		public void testEditProductNumberLessThan1() {
			Product toChange = prod;
			toChange.setProductnumber("0");
			try {
				Mockito.when(prodServ.updateProduct(1, toChange)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing product's number to a number that's greater than 0")
		public void testEditProductNumberMoreThan1() {
			Product toChange = prod;
			toChange.setProductnumber("2");
			try {
				Mockito.when(prodServ.updateProduct(1, toChange)).thenReturn(toChange);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing product's days to manufacture to a number that's less than 1, throws exception")
		public void testEditProductDaysManufactureLessThan1() {
			Product toChange = prod;
			toChange.setDaystomanufacture(0);
			try {
				Mockito.when(prodServ.updateProduct(1, toChange)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing product's days to manufacture to a number that's greater than 0")
		public void testEditProductDaysManufactureMoreThan1() {
			Product toChange = prod;
			toChange.setDaystomanufacture(2);
			try {
				Mockito.when(prodServ.updateProduct(1, toChange)).thenReturn(toChange);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing product's sell start and end date (Both Null), throws exception")
		public void testEditProductSellDatesBad1() {
			Product toChange = prod;
			toChange.setSellenddate(null);
			toChange.setSellstartdate(null);
			try {
				Mockito.when(prodServ.updateProduct(1, toChange)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing product's sell start and end date (End before Start), throws exception")
		public void testEditProductSellDatesBad2() {
			Product toChange = prod;
			toChange.setSellenddate(new Timestamp(System.currentTimeMillis()-1000));
			toChange.setSellstartdate(new Timestamp(System.currentTimeMillis()+1000));
			try {
				Mockito.when(prodServ.updateProduct(1, toChange)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing product's sell start and end date (End is null), throws exception")
		public void testEditProductSellDatesBad3() {
			Product toChange = prod;
			toChange.setSellenddate(null);
			toChange.setSellstartdate(new Timestamp(System.currentTimeMillis()));
			try {
				Mockito.when(prodServ.updateProduct(1, toChange)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing product's sell start and end date (Start is null), throws exception")
		public void testEditProductSellDatesBad4() {
			Product toChange = prod;
			toChange.setSellenddate(new Timestamp(System.currentTimeMillis()));
			toChange.setSellstartdate(null);
			try {
				Mockito.when(prodServ.updateProduct(1, toChange)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing product's sell start and end date")
		public void testEditProductSellDatesCorrect() {
			Product toChange = prod;
			toChange.setSellenddate(new Timestamp(System.currentTimeMillis()+1000));
			toChange.setSellstartdate(new Timestamp(System.currentTimeMillis()-1000));
			try {
				Mockito.when(prodServ.updateProduct(1, toChange)).thenReturn(toChange);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(prodRepository).findById(1);
		}
	}*/
}
