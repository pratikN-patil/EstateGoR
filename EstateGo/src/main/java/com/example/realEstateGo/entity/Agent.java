package com.example.realEstateGo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "Agent")
public class Agent extends Users {
	@Enumerated(value = EnumType.STRING)
	private AgentProfession agentProfession;
	@OneToMany(mappedBy = "agent")
	@JsonIgnore
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Property> properties;

}
