package com.example.realEstateGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.realEstateGo.entity.Admin;
import com.example.realEstateGo.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@PostMapping("/addadmin")
	public Admin addNewAdmin(@RequestBody Admin admin) {
		
		
		return adminService.addNewAdmin(admin);
		
	}
	
}
