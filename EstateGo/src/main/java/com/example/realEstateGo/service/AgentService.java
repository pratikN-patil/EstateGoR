package com.example.realEstateGo.service;

import java.util.List;

import com.example.realEstateGo.Dto.AgentDTO;
import com.example.realEstateGo.entity.Agent;
import com.example.realEstateGo.exception.ResourceNotFoundException;

public interface AgentService {
	public Agent addNewAgent(Agent b);

	public Agent updateAgent(int agentId, Agent b) throws ResourceNotFoundException;;

	public List<AgentDTO> getAllAgents();

	public List<AgentDTO> findAgentById(int id) throws ResourceNotFoundException;;
}
