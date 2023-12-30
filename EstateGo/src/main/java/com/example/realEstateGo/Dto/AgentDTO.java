package com.example.realEstateGo.Dto;

import com.example.realEstateGo.entity.AgentProfession;
import com.example.realEstateGo.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {
	private int id;
	private String name;
	private String email;
	private String contact;
	private Role role;
	private AgentProfession agentProfession;
}
