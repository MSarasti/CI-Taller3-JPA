package com.taller3.model.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.taller3.model.validation.addValidation;
import com.taller3.model.validation.updateValidation;

/**
 * The persistent class for the salesorderdetail database table.
 *
 */
@Entity
@NamedQuery(name = "Salesorderdetail.findAll", query = "SELECT s FROM Salesorderdetail s")
public class Salesorderdetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SALESORDERDETAIL_SALESORDERDETAILID_GENERATOR", allocationSize = 1, sequenceName = "SALESORDERDETAIL_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALESORDERDETAIL_SALESORDERDETAILID_GENERATOR")
	private Integer salesorderdetailid;
	
	/*@EmbeddedId
	private SalesorderdetailPK id;*/

	private String carriertrackingnumber;

	private Timestamp modifieddate;

	private Integer orderqty;

	private Integer rowguid;

	@NotNull(groups={addValidation.class, updateValidation.class}, message="Unit price cannot be blank")
	@Digits(groups={addValidation.class, updateValidation.class}, message="Unit price must be a number", fraction = 1000, integer = 1000)
	@Positive(groups={addValidation.class, updateValidation.class}, message="Unit price must be greater than 0")
	private BigDecimal unitprice;

	@NotNull(groups={addValidation.class, updateValidation.class}, message="Unit price discount cannot be blank")
	@Digits(groups={addValidation.class, updateValidation.class}, message="Unit price discount must be a number", fraction = 10000000, integer = 1)
	@PositiveOrZero(groups={addValidation.class, updateValidation.class}, message="Unit price discount must be non negative")
	private BigDecimal unitpricediscount;

	// bi-directional many-to-one association to Specialofferproduct
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "productid", referencedColumnName = "productid"),
			@JoinColumn(name = "specialofferid", referencedColumnName = "specialofferid") })
	private Specialofferproduct specialofferproduct;

	@Transient
	public Integer pId;
	
	@Transient
	public Integer soId;
	
	public Salesorderdetail() {
	}

	public String getCarriertrackingnumber() {
		return this.carriertrackingnumber;
	}
	
	public Integer getSalesOrderDetailId() {
		return this.salesorderdetailid;
	}

	/*public SalesorderdetailPK getId() {
		return this.id;
	}*/

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Integer getOrderqty() {
		return this.orderqty;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public Specialofferproduct getSpecialofferproduct() {
		return this.specialofferproduct;
	}

	public BigDecimal getUnitprice() {
		return this.unitprice;
	}

	public BigDecimal getUnitpricediscount() {
		return this.unitpricediscount;
	}

	public void setCarriertrackingnumber(String carriertrackingnumber) {
		this.carriertrackingnumber = carriertrackingnumber;
	}
	
	public void setSalesOrderDetailId(Integer id) {
		this.salesorderdetailid = id;
	}
	
	/*public void setId(SalesorderdetailPK id) {
		this.id = id;
	}*/

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setOrderqty(Integer orderqty) {
		this.orderqty = orderqty;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSpecialofferproduct(Specialofferproduct specialofferproduct) {
		this.specialofferproduct = specialofferproduct;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public void setUnitpricediscount(BigDecimal unitpricediscount) {
		this.unitpricediscount = unitpricediscount;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getsoId() {
		return soId;
	}

	public void setsoId(Integer soId) {
		this.soId = soId;
	}
}