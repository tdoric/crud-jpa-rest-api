package com.job.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;  
	@NotEmpty
    @Size(min = 10, max = 10, message = "Exactly 10 characters")
	@Column(name="code")
	private String code;
	@Column(name="name")
	private String name;
	@Column(name="price_hrk")
	private BigDecimal priceHrk;
	@Column(name="price_eur")
	private BigDecimal priceEur;
	@Column(name="description")
	private String description;
	@Column(name="is_available")
	private boolean isAvailable;
	
	

}
