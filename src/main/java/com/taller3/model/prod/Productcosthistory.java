package com.taller3.model.prod;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the productcosthistory database table.
 * 
 */
@Entity
@NamedQuery(name="Productcosthistory.findAll", query="SELECT p FROM Productcosthistory p")
public class Productcosthistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductcosthistoryPK id;

	private Timestamp enddate;

	private Timestamp modifieddate;

	private BigDecimal standardcost;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productid", insertable=false, updatable=false)
	private Product product;

	public Productcosthistory() {
	}

	public ProductcosthistoryPK getId() {
		return this.id;
	}

	public void setId(ProductcosthistoryPK id) {
		this.id = id;
	}

	public Timestamp getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public BigDecimal getStandardcost() {
		return this.standardcost;
	}

	public void setStandardcost(BigDecimal standardcost) {
		this.standardcost = standardcost;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}