
package com.example.realEstateGo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.realEstateGo.Dto.CustomerDTO;
import com.example.realEstateGo.entity.Customer;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.service.CustomerService;
import jakarta.validation.Valid;

@RestController

public class CustomerController {
	@Autowired
	CustomerService customerService;

	@PostMapping("/customer/customer")
	public Customer addNewCustomer(@RequestBody Customer c) {
		return customerService.addNewCustomer(c);
	}

	@GetMapping("/customer/customers")
	public List<CustomerDTO> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/customer/view/{customerId}")
	public ResponseEntity<Object> getCustomerById(@PathVariable int customerId) {
		try {
			List<CustomerDTO> customer = customerService.findCustomerById(customerId);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			Map<String, String> map = new HashMap<>();
			map.put("errorMessage", ex.getMessage());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/customer/update/{customerId}")
	public ResponseEntity<Object> updateCustomer(@PathVariable int customerId,
			@Valid @RequestBody Customer updatedCustomer) {
		try {
			Customer updatedC = customerService.updateCustomer(customerId, updatedCustomer);
			return new ResponseEntity<>(updatedC, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			Map<String, String> map = new HashMap<>();
			map.put("errorMessage", ex.getMessage());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

//    @DeleteMapping("/{custid}")
//    public String deleteCustomer(@PathVariable int custid) {
//    	customerServiceImpl.deleteCustomer(custid);
//        return "Customer with id " + custid + " deleted successfully.";
//    }
}
