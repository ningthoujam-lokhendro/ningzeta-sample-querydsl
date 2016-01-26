/**
 * 
 */
package com.ningzeta.sample.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.ningzeta.sample.querydsl.model.Customer;

/**
 * @author Ningthoujam Lokhendro
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>,
											QueryDslPredicateExecutor<Customer> {
	
	
}
