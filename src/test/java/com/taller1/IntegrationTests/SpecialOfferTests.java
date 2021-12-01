package com.taller1.IntegrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.taller3.model.sales.Specialoffer;
import com.taller3.repository.SpecialofferRepository;
import com.taller3.service.interfaces.SpecialofferService;

@SpringBootTest
public class SpecialOfferTests {/*
	//@Mock
	//private SpecialofferRepository soRepo;
	
	//@InjectMocks
	@Autowired
	public SpecialofferService soServ;
	
	public Specialoffer so;
	
	@BeforeEach
	public void setUp1() {
		so = new Specialoffer();
		so.setSpecialofferid(1);
		so.setCategory("Special Category");
		so.setDiscountpct(BigDecimal.valueOf(5));
		so.setModifieddate(new Timestamp(System.currentTimeMillis()));
		so.setDescription("Description");
		so.setStartdate(new Timestamp(System.currentTimeMillis()-100));
		so.setEnddate(new Timestamp(System.currentTimeMillis()+1000));
	}
	
	@Test
	public void test() {
		assertTrue(true);
	}
	
	@Nested
	@DisplayName("Add SpecialOffer Tests")
	class addSpecialOfferTests {
		@Test
		@DisplayName("Add null SpecialOffer, throws exception")
		public void testAddNullSpecialOffer() {
			assertThrows(Exception.class, () -> soServ.saveSpecialOffer(null));
		}
		
		@Test
		@DisplayName("Add SpecialOffer with null category, throws exception")
		public void testAddSpecialOfferWithNullCategory() {
			so.setCategory(null);
			assertThrows(Exception.class, () -> soServ.saveSpecialOffer(so));
		}
		
		@Test
		@DisplayName("Add SpecialOffer with discount less than 0, throws exception")
		public void testAddSpecialOfferWithDiscountLessThan0() {
			so.setDiscountpct(BigDecimal.valueOf(-1));
			assertThrows(Exception.class, () -> soServ.saveSpecialOffer(so));
		}
		
		@Test
		@DisplayName("Add SpecialOffer with modified date before now, throws exception")
		public void testAddSpecialOfferWithModDateBeforeNow() {
			so.setModifieddate(new Timestamp(System.currentTimeMillis()-100000));
			assertThrows(Exception.class, () -> soServ.saveSpecialOffer(so));
		}
		
		@Test
		@DisplayName("Add SpecialOffer correctly")
		public void testAddSpecialOffer() {
			assertDoesNotThrow(() -> soServ.saveSpecialOffer(so));
			assertEquals(so.getSpecialofferid(), soServ.searchSpecialOffer(1).getSpecialofferid());
			assertEquals(so.getDescription(), soServ.searchSpecialOffer(1).getDescription());
		}
	}
	
	@Nested
	@DisplayName("Edit SpecialOffer Tests")
	class editSpecialOfferTests {
		@Test
		@DisplayName("Edit a non-existing special offer, throws exception")
		public void testEditSpecialOfferNoSpecial() {
			assertThrows(Exception.class, () -> soServ.updateSpecialOffer(0, so));
		}
		
		@Test
		@DisplayName("Edit an existing special offer to null, throws exception")
		public void testEditSpecialOfferToNull() {
			assertThrows(Exception.class, () -> soServ.updateSpecialOffer(1, null));
		}
		
		@Test
		@DisplayName("Edit an existing special offer's category to null, throws exception")
		public void testEditSpecialOfferWithNullCategory() {
			so.setCategory(null);
			assertThrows(Exception.class, () -> soServ.updateSpecialOffer(1, so));
		}

		@Test
		@DisplayName("Edit an existing special offer's discount to 0, throws exception")
		public void testEditSpecialOfferWith0Discount() {
			so.setDiscountpct(BigDecimal.valueOf(0));
			assertThrows(Exception.class, () -> soServ.updateSpecialOffer(1, so));
		}
		
		@Test
		@DisplayName("Edit an existing special offer correctly")
		public void testEditSpecialOfferCorrectly() {
			so.setMaxqty(15);
			so.setMinqty(5);
			assertDoesNotThrow(() -> soServ.updateSpecialOffer(1, so));
			assertEquals(so.getDescription(), soServ.searchSpecialOffer(1).getDescription());
		}
	}*/
}
