package com.example.realEstateGo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.realEstateGo.Dto.AgentDTO;
import com.example.realEstateGo.entity.Agent;
import com.example.realEstateGo.exception.ResourceNotFoundException;
import com.example.realEstateGo.service.AgentService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AgentService agentService;

	@PostMapping("/agent/add")
	public Agent addNewAgent(@RequestBody Agent agent) {
		return agentService.addNewAgent(agent);
	}

	@GetMapping("/agent/agents")
	public List<AgentDTO> getAllAgents() {
		return agentService.getAllAgents();
	}

	@GetMapping("/agent/{agentId}")
	public ResponseEntity<Object> getAgentById(@PathVariable int agentId) {
		try {
			List<AgentDTO> agent = agentService.findAgentById(agentId);
			return new ResponseEntity<>(agent, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			Map<String, String> map = new HashMap<>();
			map.put("errorMessage", ex.getMessage());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/agent/update/{agentId}")
	public ResponseEntity<Object> updateAgent(@PathVariable int agentId, @Valid @RequestBody Agent updatedAgent) {
		try {
			Agent updatedAgentResult = agentService.updateAgent(agentId, updatedAgent);
			return new ResponseEntity<>(updatedAgentResult, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			Map<String, String> map = new HashMap<>();
			map.put("errorMessage", ex.getMessage());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

//    @DeleteMapping("/{agentId}")
//    public String deleteAgent(@PathVariable int agentId) {
//        agentService.deleteAgent(agentId);
//        return "Agent with id " + agentId + " deleted successfully.";
//    }
}