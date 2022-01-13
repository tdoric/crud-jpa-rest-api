package com.job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	  List<Product> findByName(String name);

}
