package com.taller3.model.sales;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the countryregioncurrency database table.
 * 
 */
@Entity
@NamedQuery(name="Countryregioncurrency.findAll", query="SELECT c FROM Countryregioncurrency c")
public class Countryregioncurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CountryregioncurrencyPK id;

	private Timestamp modifieddate;

	//bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name="currencycode", insertable=false, updatable=false)
	private Currency currency;

	public Countryregioncurrency() {
	}

	public CountryregioncurrencyPK getId() {
		return this.id;
	}

	public void setId(CountryregioncurrencyPK id) {
		this.id = id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}