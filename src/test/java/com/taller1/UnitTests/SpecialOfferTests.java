package com.taller1.UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.*;
import java.sql.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.taller3.model.sales.Specialoffer;
import com.taller3.repository.SpecialofferRepository;
import com.taller3.service.implementation.SpecialOfferServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SpecialOfferTests {/*
	@Mock
	private SpecialofferRepository soRepo;
	
	@InjectMocks
	public SpecialOfferServiceImpl soServ;
	
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
			try {
				Mockito.when(soServ.saveSpecialOffer(null)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(soRepo);
		}
		
		@Test
		@DisplayName("Add SpecialOffer with null category, throws exception")
		public void testAddSpecialOfferWithNullCategory() {
			so.setCategory(null);
			try {
				Mockito.when(soServ.saveSpecialOffer(so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(soRepo);
		}
		
		@Test
		@DisplayName("Add SpecialOffer with null discount, throws exception")
		public void testAddSpecialOfferWithNullDiscount() {
			so.setDiscountpct(null);
			try {
				Mockito.when(soServ.saveSpecialOffer(so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(soRepo);
		}
		
		@Test
		@DisplayName("Add SpecialOffer with discount less than 0, throws exception")
		public void testAddSpecialOfferWithDiscountLessThan0() {
			so.setDiscountpct(BigDecimal.valueOf(-1));
			try {
				Mockito.when(soServ.saveSpecialOffer(so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(soRepo);
		}
		
		@Test
		@DisplayName("Add SpecialOffer with null modified date, throws exception")
		public void testAddSpecialOfferWithNullModDate() {
			so.setModifieddate(null);
			try {
				Mockito.when(soServ.saveSpecialOffer(so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(soRepo);
		}
		
		@Test
		@DisplayName("Add SpecialOffer with modified date before now, throws exception")
		public void testAddSpecialOfferWithModDateBeforeNow() {
			so.setModifieddate(new Timestamp(System.currentTimeMillis()-100000));
			try {
				Mockito.when(soServ.saveSpecialOffer(so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(soRepo);
		}
		
		@Test
		@DisplayName("Add SpecialOffer with modified date before now, throws exception")
		public void testAddSpecialOfferWithModDateAfterNow() {
			so.setModifieddate(new Timestamp(System.currentTimeMillis()+100000));
			try {
				Mockito.when(soServ.saveSpecialOffer(so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoInteractions(soRepo);
		}
		
		@Test
		@DisplayName("Add SpecialOffer correctly")
		public void testAddSpecialOffer() {
			try {
				lenient().when(soServ.saveSpecialOffer(so)).thenReturn(so);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verifyNoMoreInteractions(soRepo);
		}
	}
	
	@Nested
	@DisplayName("Edit SpecialOffer Tests")
	class editSpecialOfferTests {
		@BeforeEach
		public void setUpAdd() {
			try {
				soServ.saveSpecialOffer(so);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		@Test
		@DisplayName("Edit a non-existing special offer, throws exception")
		public void testEditSpecialOfferNoSpecial() {
			try {
				Mockito.when(soServ.updateSpecialOffer(0, so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(0);
		}
		
		@Test
		@DisplayName("Edit an existing special offer to null, throws exception")
		public void testEditSpecialOfferToNull() {
			try {
				Mockito.when(soServ.updateSpecialOffer(1, null)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing special offer's category to null, throws exception")
		public void testEditSpecialOfferWithNullCategory() {
			so.setCategory(null);
			try {
				Mockito.when(soServ.updateSpecialOffer(1, so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing special offer's discount to null, throws exception")
		public void testEditSpecialOfferWithNullDiscount() {
			so.setDiscountpct(null);
			try {
				Mockito.when(soServ.updateSpecialOffer(1, so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing special offer's discount to negative, throws exception")
		public void testEditSpecialOfferWithNegativeDiscount() {
			so.setDiscountpct(BigDecimal.valueOf(-1));
			try {
				Mockito.when(soServ.updateSpecialOffer(1, so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing special offer's discount to 0, throws exception")
		public void testEditSpecialOfferWith0Discount() {
			so.setDiscountpct(BigDecimal.valueOf(0));
			try {
				Mockito.when(soServ.updateSpecialOffer(1, so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing special offer's modified date to null, throws exception")
		public void testEditSpecialOfferWithNullModDate() {
			so.setModifieddate(null);
			try {
				Mockito.when(soServ.updateSpecialOffer(1, so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing special offer's modified date is before now, throws exception")
		public void testEditSpecialOfferWithBeforeModDate() {
			so.setModifieddate(new Timestamp(System.currentTimeMillis()-100000));
			try {
				Mockito.when(soServ.updateSpecialOffer(1, so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing special offer's modified date is after now, throws exception")
		public void testEditSpecialOfferWithAfterModDate() {
			so.setModifieddate(new Timestamp(System.currentTimeMillis()+100000));
			try {
				Mockito.when(soServ.updateSpecialOffer(1, so)).thenThrow(Exception.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(1);
		}
		
		@Test
		@DisplayName("Edit an existing special offer correctly")
		public void testEditSpecialOfferCorrectly() {
			so.setMaxqty(15);
			so.setMinqty(5);
			try {
				Mockito.when(soServ.updateSpecialOffer(1, so)).thenReturn(so);
			} catch (Exception e) {
				e.printStackTrace();
			}
			verify(soRepo).findById(1);
		}
	}*/
}
