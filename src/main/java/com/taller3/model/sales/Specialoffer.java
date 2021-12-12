package com.taller3.model.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.taller3.model.validation.*;

/**
 * The persistent class for the specialoffer database table.
 *
 */
@Entity
@NamedQuery(name = "Specialoffer.findAll", query = "SELECT s FROM Specialoffer s")
public class Specialoffer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SPECIALOFFER_SPECIALOFFERID_GENERATOR", allocationSize = 1, sequenceName = "SPECIALOFFER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPECIALOFFER_SPECIALOFFERID_GENERATOR")
	private Integer specialofferid;

	@NotBlank(groups={addValidation.class, updateValidation.class}, message="Category cannot be blank")
	private String category;

	private String description;
	
	@NotNull(groups={addValidation.class, updateValidation.class}, message="Discount cannot be blank")
	@Digits(groups={addValidation.class, updateValidation.class}, message="Discount must be a decimal", fraction = 100000, integer = 1)
	@Positive(groups={addValidation.class, updateValidation.class}, message="Discount must be greater than 0")
	private BigDecimal discountpct;

	private Timestamp enddate;

	private Integer maxqty;

	private Integer minqty;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(groups={addValidation.class, updateValidation.class}, message="Modified date cannot be blank")
	private LocalDate modifieddate;

	private Integer rowguid;

	private Timestamp startdate;

	private String type;

	// bi-directional many-to-one association to Specialofferproduct
	@OneToMany(mappedBy = "specialoffer")
	private List<Specialofferproduct> specialofferproducts;

	public Specialoffer() {
	}

	public Specialofferproduct addSpecialofferproduct(Specialofferproduct specialofferproduct) {
		if(this.specialofferproducts==null) {
			this.specialofferproducts = new ArrayList<>();
		}
		getSpecialofferproducts().add(specialofferproduct);
		specialofferproduct.setSpecialoffer(this);

		return specialofferproduct;
	}

	public String getCategory() {
		return this.category;
	}

	public String getDescription() {
		return this.description;
	}

	public BigDecimal getDiscountpct() {
		return this.discountpct;
	}

	public Timestamp getEnddate() {
		return this.enddate;
	}

	public Integer getMaxqty() {
		return this.maxqty;
	}

	public Integer getMinqty() {
		return this.minqty;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public Integer getSpecialofferid() {
		return this.specialofferid;
	}

	public List<Specialofferproduct> getSpecialofferproducts() {
		return this.specialofferproducts;
	}

	public Timestamp getStartdate() {
		return this.startdate;
	}

	public String getType() {
		return this.type;
	}

	public Specialofferproduct removeSpecialofferproduct(Specialofferproduct specialofferproduct) {
		getSpecialofferproducts().remove(specialofferproduct);
		specialofferproduct.setSpecialoffer(null);

		return specialofferproduct;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDiscountpct(BigDecimal discountpct) {
		this.discountpct = discountpct;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public void setMaxqty(Integer maxqty) {
		this.maxqty = maxqty;
	}

	public void setMinqty(Integer minqty) {
		this.minqty = minqty;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSpecialofferid(Integer specialofferid) {
		this.specialofferid = specialofferid;
	}

	public void setSpecialofferproducts(List<Specialofferproduct> specialofferproducts) {
		this.specialofferproducts = specialofferproducts;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public void setType(String type) {
		this.type = type;
	}

}