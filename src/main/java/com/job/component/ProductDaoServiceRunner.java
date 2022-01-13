package com.job.component;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.job.dao.ProductDaoService;
import com.job.model.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductDaoServiceRunner implements CommandLineRunner {
	
	
	private ProductDaoService productDaoService;
	
	public ProductDaoServiceRunner(ProductDaoService productDaoService) {
		super();
		this.productDaoService = productDaoService;
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = Product.builder()
				.code("9876543210")
				.name("novi")
				.priceHrk(BigDecimal.valueOf(15.50))
				.priceEur(BigDecimal.valueOf(1))
				.description("desc")
				.isAvailable(false).build();
		long insert = productDaoService.insert(product);
		log.info("New product is created : " + insert);
		
	}

}
