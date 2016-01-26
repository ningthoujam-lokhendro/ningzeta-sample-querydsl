/**
 * 
 */
package com.ningzeta.sample.querydsl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ningzeta.sample.querydsl.model.Country;
import com.ningzeta.sample.querydsl.model.Customer;
import com.ningzeta.sample.querydsl.service.CustomerService;

/**
 * @author Ningthoujam Lokhendro
 *
 */
@Controller
public class MainController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("countries", this.customerService.getAllCountries());
		return "index";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllCustomer(Model model) {
		model.addAttribute("title", "All Customer");
		model.addAttribute("customers", this.customerService.getAllCustomer());
		return "all";
	}
	
	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public String getCustomerByName(@RequestParam("name") String country,
									Model model) {
		model.addAttribute("title", "Customer of Country '" + country + "'" );
		model.addAttribute("customers", this.customerService.getCustomerFrom(country));
		
		return "all";
	}
	
	@RequestMapping(value = "/age", method = RequestMethod.GET)
	public String getCustomerByAgeGroup(@RequestParam("start") int startAge,
										@RequestParam("end") int endAge,
										Model model) {
		model.addAttribute("title", "Customer of Age Range - [" + startAge + " to "+ endAge +"]" );
		model.addAttribute("customers", this.customerService.getCustomerAgeGroup(startAge, endAge));
		
		return "all";
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String getCustomerInfo(@RequestParam("name") String name,
								  Model model) {
		Customer customer = this.customerService.getCustomerByName(name);
		Country country = customer.getCountry();
		
		model.addAttribute("title", "Customer Information" );
		model.addAttribute("customer", customer);
		model.addAttribute("country", country);
		
		return "info";
	}
	
	
}
