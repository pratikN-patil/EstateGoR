package com.example.realEstateGo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Name can't be empty.")
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Only alphabets are allowed.")
	@Size(min = 3, max = 40, message = "Minimum 3 and maximum 40 characters allowed.")
	private String name;

	@NotEmpty(message = "Email can't be empty.")
	@Email(message = "Invalid email format.")
	@Size(max = 50, message = "Maximum 50 characters allowed for email.")
	private String email;

	@NotEmpty(message = "Address can't be empty.")
	@Size(max = 255, message = "Maximum 255 characters allowed for address.")
	private String address;

	@NotEmpty(message = "Contact number is required.")
	@Pattern(regexp = "^[0-9]*$", message = "Only numeric values are allowed.")
	@Size(min = 10, max = 15, message = "Contact number should be between 10 and 15 digits.")
	@Column(unique = true)
	private String contact;

	@Column(unique = true)
	@NotEmpty(message = "Username can't be empty.")
	private String username;

	@NotEmpty(message = "Password can't be empty.")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Role role;

}
