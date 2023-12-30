package com.example.realEstateGo.service;

import java.util.List;

import com.example.realEstateGo.Dto.CustomerDTO;
import com.example.realEstateGo.entity.Customer;
import com.example.realEstateGo.exception.ResourceNotFoundException;

public interface CustomerService {
	public Customer addNewCustomer(Customer b);

	public Customer updateCustomer(int customerId, Customer updatedCustomer) throws ResourceNotFoundException;;

	public List<CustomerDTO> getAllCustomers() ;

	public List<CustomerDTO> findCustomerById(int id) throws ResourceNotFoundException;
//	getCustomerById
	
//	public void deleteCustomer(int customerId);
}
