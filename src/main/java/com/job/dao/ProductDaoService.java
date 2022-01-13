package com.job.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.job.model.Product;

@Repository
@Transactional
public class ProductDaoService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(Product product){
		entityManager.persist(product);
		return product.getId();
	}

}
