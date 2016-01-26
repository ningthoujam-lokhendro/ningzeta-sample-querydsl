/**
 * 
 */
package com.ningzeta.sample.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.ningzeta.sample.querydsl.model.Country;

/**
 * @author Ningthoujam Lokhendro
 *
 */
public interface CountryRepository extends JpaRepository<Country, Long>,
											QueryDslPredicateExecutor<Country>{

}
