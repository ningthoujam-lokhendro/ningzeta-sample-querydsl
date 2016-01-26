/**
 * 
 */
package com.ningzeta.sample.querydsl.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.ningzeta.example.querydsl.model.QCountry;
import com.ningzeta.example.querydsl.model.QCustomer;
import com.ningzeta.sample.querydsl.model.Country;
import com.ningzeta.sample.querydsl.model.Customer;
import com.ningzeta.sample.querydsl.repository.CountryRepository;
import com.ningzeta.sample.querydsl.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	/**
	 * Find customer whose name equal to the name provided. 
	 * @param id
	 * @return
	 */
	public Customer getCustomerByName(String name) {
		
		QCustomer customer =  QCustomer.customer;
		Predicate predicate = customer.name.eq(name);
		
		return this.customerRepository.findOne(predicate);
	}
	
	/**
	 * Get all customer whose country is provided.
	 * @param country
	 * @return
	 */
	public List<Customer> getCustomerFrom(String country) {
		List<Customer> customers = new ArrayList<Customer>();
		// use join expression any(). Restriction on the join. 
		// For multiple restriction use JPASubQuery().
		BooleanExpression isCustomerCountry = QCustomer.customer.country.name.eq(country);
		
		Iterator<Customer> itr = this.customerRepository.findAll(isCustomerCountry).iterator();
		while(itr.hasNext())
			customers.add(itr.next());
		
		return customers;
	}
	
	public List<Customer> getCustomerAgeGroup(int startAge, int endAge) {
		List<Customer> customers = new ArrayList<Customer>();
		QCustomer customer = QCustomer.customer;
		Predicate cus = customer.age.between(startAge, endAge);
		
		Iterator<Customer> itr = this.customerRepository.findAll(cus).iterator();
		while(itr.hasNext())
			customers.add(itr.next());
		
		return customers;
	}
	
	/**
	 * Get all customer sorted by name of the customer.
	 * @return
	 */
	public List<Customer> getAllCustomer() {
		List<Customer> customers = new ArrayList<Customer>();
		
		OrderSpecifier<String> sortAsc = QCustomer.customer.name.asc();
		Iterator<Customer> itr = this.customerRepository.findAll(sortAsc).iterator();
		while(itr.hasNext())
			customers.add(itr.next());
		return customers;
	}
	
	public List<String> getAllCountries() {
		List<String> countries= new ArrayList<String>();
		
		OrderSpecifier<String> sortAsc = QCountry.country.name.asc();
		Iterable<Country> fetchCountries = this.countryRepository.findAll(sortAsc);
		Iterator<Country> itr = fetchCountries.iterator();
		while(itr.hasNext()){
			countries.add(itr.next().getName());
		}

		return countries;
	}
}
