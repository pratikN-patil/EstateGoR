package com.example.realEstateGo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.realEstateGo.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByName(String username);

}
