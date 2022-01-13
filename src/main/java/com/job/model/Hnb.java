package com.job.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hnb {
	
	@JsonProperty("broj_tecajnice")
	private String brojTecajnice;
	@JsonProperty("datum_primjene")
	private String datumPrimjene;
	private String drzava;
	@JsonProperty("drzava_iso")
	private String drzavaIso;
	@JsonProperty("sifra_valute")
	private String sifraValute;
	private String valuta;
	private int jedinica;
	@JsonProperty("kupovni_tecaj")
	private String kupovniTecaj;
	@JsonProperty("srednji_tecaj")
	private String srednjiTecaj;
	@JsonProperty("prodajni_tecaj")
	private String prodajniTecaj;

}
