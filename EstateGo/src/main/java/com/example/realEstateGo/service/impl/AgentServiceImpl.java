package com.example.realEstateGo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.realEstateGo.Dto.AgentDTO;
import com.example.realEstateGo.Dto.PropertyDTO;
import com.example.realEstateGo.entity.Agent;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.repository.AgentRepository;
import com.example.realEstateGo.service.AgentService;
import com.example.realEstateGo.service.EmailNotificationService;

@Service
public class AgentServiceImpl implements AgentService {
	@Autowired
	AgentRepository agentRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private EmailNotificationService emailNotificationService;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Agent addNewAgent(Agent a) {
		a.setPassword(passwordEncoder.encode(a.getPassword()));
		notifyNewCustomerEmail(a);
		return agentRepository.save(a);
	}

	private void notifyNewCustomerEmail(Agent a) {
		String customerEmail = a.getEmail().trim();
		emailNotificationService.sendWelcomeEmail(customerEmail, a.getName());
	}

	@Override
	public Agent updateAgent(int agentId, Agent updatedAgent) throws ResourceNotFoundException {
		Optional<Agent> existingAgentOptional = agentRepository.findById(agentId);

		if (existingAgentOptional.isPresent()) {
			Agent existingAgent = existingAgentOptional.get();
			// Update fields of the existing agent with the values from updatedAgent
			existingAgent.setName(updatedAgent.getName());
			existingAgent.setContact(updatedAgent.getContact());
			existingAgent.setAddress(updatedAgent.getAddress());
			existingAgent.setEmail(updatedAgent.getEmail());
			existingAgent.setAgentProfession(updatedAgent.getAgentProfession());
			existingAgent.setUsername(updatedAgent.getUsername());
			existingAgent.setPassword(updatedAgent.getPassword());

			return agentRepository.save(existingAgent);
		} else {
			// Handle the case where the agent with the given ID is not found
			throw new ResourceNotFoundException("Agent", " id", agentId);
		}
	}

	@Override
	public List<AgentDTO> getAllAgents() {
		List<Agent> agents=agentRepository.findAll();
		return agents.stream().map(a -> modelMapper.map(a, AgentDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<AgentDTO> findAgentById(int agentId) throws ResourceNotFoundException {
		Optional<Agent> agentOptional = agentRepository.findById(agentId);

		if (agentOptional.isPresent()) {
			return agentOptional.stream().map(agent ->modelMapper.map(agentOptional,AgentDTO.class)).collect(Collectors.toList());
		} else {
			// Handle the case where the agent with the given ID is not found
			throw new ResourceNotFoundException("Agent", " id", agentId);
		}
	}

}
