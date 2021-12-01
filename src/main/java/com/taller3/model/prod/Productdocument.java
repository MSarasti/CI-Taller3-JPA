package com.taller3.model.prod;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the productdocument database table.
 * 
 */
@Entity
@NamedQuery(name="Productdocument.findAll", query="SELECT p FROM Productdocument p")
public class Productdocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductdocumentPK id;

	private Timestamp modifieddate;

	//bi-directional many-to-one association to Document
	@ManyToOne
	@JoinColumn(name="documentnode", insertable=false, updatable=false)
	private Document document;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productid", insertable=false, updatable=false)
	private Product product;

	public Productdocument() {
	}

	public ProductdocumentPK getId() {
		return this.id;
	}

	public void setId(ProductdocumentPK id) {
		this.id = id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}