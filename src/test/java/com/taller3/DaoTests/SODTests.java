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
public class SODTests {
	@Autowired
	public SalesOrderDetailDaoImpl sodDao;
	
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
	
	public SpecialofferproductPK pk;
	
	public Salesorderdetail sod;
	
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
		pk = new SpecialofferproductPK();
		pk.setProductid(1);
		pk.setSpecialofferid(1);
		sop.setId(pk);
		sop.setModifieddate(LocalDate.now());
		sop.setSpecialoffer(so);
		
		if(sopDao.findById(pk)==null)
			sopDao.save(sop);
		else
			sopDao.update(sop);
		
		sod = new Salesorderdetail();
		sod.setSpecialofferproduct(sop);
		sod.setOrderqty(5);
		sod.setUnitprice(BigDecimal.valueOf(1.25));
	}
	
	@Nested
	@DisplayName("Add sale order detail tests")
	class addSODTests{
		
		@Test
		@DisplayName("Adds a sale order detail correctly")
		public void testAddSODCorrectly() {
			sodDao.save(sod);
			assertEquals(1, sodDao.findAll().size());
		}
	}

	@Nested
	@DisplayName("Edit sale order detail tests")
	class editSODTests{
		@Test
		@DisplayName("Edit an existing sales order detail's modified date")
		public void testEditSODCategory() {
			Salesorderdetail sod1 = sodDao.findById(1);
			sod1.setModifieddate(Timestamp.valueOf(LocalDate.now().plusDays(5).atStartOfDay()));
			sodDao.update(sod1);
			assertEquals(Timestamp.valueOf(LocalDate.now().plusDays(5).atStartOfDay()), sodDao.findById(1).getModifieddate());
		}
	}
	@Nested
	@DisplayName("Find sale order detail tests")
	class findSODTests{
		@Test
		@DisplayName("Find an existing sales order detail")
		public void testFindSOD() {
			Salesorderdetail found = sodDao.findById(1);
			assertEquals("1.25", found.getUnitprice().toString());
		}
		
		@Test
		@DisplayName("Find all sales order details")
		public void testFindAllSOD() {
			List<Salesorderdetail> list = sodDao.findAll();
			assertEquals(1, list.size());
		}
	}
}
