package com.example.realEstateGo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.realEstateGo.entity.Admin;
import com.example.realEstateGo.repository.AdminRepository;
import com.example.realEstateGo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public Admin addNewAdmin(Admin b) {
		b.setPassword(passwordEncoder.encode(b.getPassword()));                                              
		return adminRepository.save(b);
	}

}
