package com.taller3.model.sales;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.taller3.model.validation.addValidation;
import com.taller3.model.validation.updateValidation;

/**
 * The persistent class for the specialofferproduct database table.
 *
 */
@Entity
@NamedQuery(name = "Specialofferproduct.findAll", query = "SELECT s FROM Specialofferproduct s")
public class Specialofferproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SpecialofferproductPK id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(groups={addValidation.class, updateValidation.class}, message="Modified date cannot be blank")
	private LocalDate modifieddate;

	private Integer rowguid;

	// bi-directional many-to-one association to Salesorderdetail
	@OneToMany(mappedBy = "specialofferproduct")
	private List<Salesorderdetail> salesorderdetails;

	// bi-directional many-to-one association to Specialoffer
	@ManyToOne
	@JoinColumn(name = "specialofferid", insertable=false, updatable=false)
	private Specialoffer specialoffer;
	
	@Transient
	public Integer pId;
	
	@Transient 
	public Integer sId;

	public Specialofferproduct() {
	}

	public Salesorderdetail addSalesorderdetail(Salesorderdetail salesorderdetail) {
		getSalesorderdetails().add(salesorderdetail);
		salesorderdetail.setSpecialofferproduct(this);

		return salesorderdetail;
	}

	public SpecialofferproductPK getId() {
		return this.id;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public List<Salesorderdetail> getSalesorderdetails() {
		return this.salesorderdetails;
	}

	public Specialoffer getSpecialoffer() {
		return this.specialoffer;
	}

	public Salesorderdetail removeSalesorderdetail(Salesorderdetail salesorderdetail) {
		getSalesorderdetails().remove(salesorderdetail);
		salesorderdetail.setSpecialofferproduct(null);

		return salesorderdetail;
	}

	public void setId(SpecialofferproductPK id) {
		this.id = id;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSalesorderdetails(List<Salesorderdetail> salesorderdetails) {
		this.salesorderdetails = salesorderdetails;
	}

	public void setSpecialoffer(Specialoffer specialoffer) {
		this.specialoffer = specialoffer;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getsId() {
		return sId;
	}

	public void setsID(Integer sId) {
		this.sId = sId;
	}

}