package com.example.realEstateGo.service.impl;

import java.util.List;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.realEstateGo.Dto.AgentDTO;
import com.example.realEstateGo.Dto.CustomerDTO;
import com.example.realEstateGo.entity.Agent;
import com.example.realEstateGo.entity.Customer;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.repository.CustomerRepository;
import com.example.realEstateGo.service.CustomerService;
import com.example.realEstateGo.service.EmailNotificationService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private EmailNotificationService emailNotificationService;

	@Override
	public Customer addNewCustomer(Customer c) {
		c.setPassword(passwordEncoder.encode(c.getPassword()));

		// Notify the customer by email
		notifyNewCustomerEmail(c);

		return customerRepository.save(c);
	}

	private void notifyNewCustomerEmail(Customer c) {
		String customerEmail = c.getEmail().trim();
		emailNotificationService.sendWelcomeEmail(customerEmail, c.getName());
	}

	@Override
	public Customer updateCustomer(int customerId, Customer updatedCustomer) throws ResourceNotFoundException {
		Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);

		if (existingCustomerOptional.isPresent()) {
			Customer existingCustomer = existingCustomerOptional.get();
			// Update fields of the existing customer with the values from updatedCustomer
			existingCustomer.setName(updatedCustomer.getName());
			existingCustomer.setContact(updatedCustomer.getContact());
			existingCustomer.setAddress(updatedCustomer.getAddress());
			existingCustomer.setEmail(updatedCustomer.getEmail());
			existingCustomer.setRole(updatedCustomer.getRole());
			existingCustomer.setUsername(updatedCustomer.getUsername());
			existingCustomer.setPassword(updatedCustomer.getPassword());

			return customerRepository.save(existingCustomer);
		} else {
			// Handle the case where the customer with the given ID is not found
			throw new ResourceNotFoundException("Customer", " id", customerId);
		}
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers=customerRepository.findAll();
		return customers.stream().map(c -> modelMapper.map(c, CustomerDTO.class)).collect(Collectors.toList());

	}

	@Override
	public List<CustomerDTO> findCustomerById(int customerId) throws ResourceNotFoundException {
		Optional<Customer> customerOptional = customerRepository.findById(customerId);

		if (customerOptional.isPresent()) {
			return customerOptional.stream().map(customer ->modelMapper.map(customerOptional,CustomerDTO.class)).collect(Collectors.toList());
		} else {
			// Handle the case where the customer with the given ID is not found
			throw new ResourceNotFoundException("Customer", " id", customerId);
		}

	}

//	@Override
//    public void deleteCustomer(int customerId) {
//        customerRepository.deleteById(customerId);
//    }

}
