package com.job.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job.model.Product;
import com.job.repository.ProductRepository;
import com.job.service.HnbService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	private ProductRepository productRepo;
	private HnbService hnbService;
	
	public ApiController(ProductRepository productRepo,HnbService hnbService) {
		super();
		this.productRepo = productRepo;
		this.hnbService=hnbService;
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllTutorials(@RequestParam(required = false) String name) {
		
		List<Product> products = new ArrayList<>();
		try {
			if (name == null)
				productRepo.findAll().forEach(products::add);
			else
				productRepo.findByName(name).forEach(products::add);

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createTutorial(@RequestBody Product product) {
		try {
			String currency = hnbService.callWebService();
			String curryn = currency.replace("," ,".");
			BigDecimal bigDecimal = new BigDecimal(curryn);
			Product prod = productRepo
					.save(Product.builder()
							.name(product.getName())
							.code(product.getCode())
							.priceEur(product.getPriceHrk().divide(bigDecimal, 2, RoundingMode.HALF_EVEN))
							.priceHrk(product.getPriceHrk())
							.description(product.getDescription())
							.isAvailable(product.isAvailable()).build());
			return new ResponseEntity<>(prod, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateTutorial(@PathVariable("id") long id, @RequestBody Product product) {
		Optional<Product> productData = productRepo.findById(id);

		if (productData.isPresent()) {
			Product prod = productData.get();
			Product newProd = Product.builder().name(product.getName())
			.code(product.getCode())
			.priceEur(product.getPriceEur())
			.priceHrk(product.getPriceHrk())
			.description(prod.getDescription())
			.isAvailable(product.isAvailable()).build();
			
			return new ResponseEntity<>(productRepo.save(newProd), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			productRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/products")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			productRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
