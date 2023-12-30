package com.example.realEstateGo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.example.realEstateGo.entity.Admin;
import com.example.realEstateGo.entity.Customer;

public interface AdminRepository extends JpaRepository<Admin, Integer>  {

}
