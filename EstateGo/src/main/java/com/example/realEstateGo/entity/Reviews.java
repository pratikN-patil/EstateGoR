package com.example.realEstateGo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reviews {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Comment can't be empty.")
    private String comment;
	
	@NotNull(message = "Rating can't be empty.")
    private float rating;
	
	@NotEmpty(message = "Date can't be empty.")
    private String date;

	@ManyToOne
	@JoinColumn(name = "custid")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "pid")
	private Property property;
	
	@Transient
	private Integer reviewsCustomerId;
	
	@Transient
	private Integer reviewsPropertyId;
}
