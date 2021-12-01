package com.taller3.model.sales;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the personcreditcard database table.
 * 
 */
@Entity
@NamedQuery(name="Personcreditcard.findAll", query="SELECT p FROM Personcreditcard p")
public class Personcreditcard implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersoncreditcardPK id;

	private Timestamp modifieddate;

	//bi-directional many-to-one association to Creditcard
	@ManyToOne
	@JoinColumn(name="creditcardid", insertable=false, updatable=false)
	private Creditcard creditcard;

	public Personcreditcard() {
	}

	public PersoncreditcardPK getId() {
		return this.id;
	}

	public void setId(PersoncreditcardPK id) {
		this.id = id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Creditcard getCreditcard() {
		return this.creditcard;
	}

	public void setCreditcard(Creditcard creditcard) {
		this.creditcard = creditcard;
	}

}