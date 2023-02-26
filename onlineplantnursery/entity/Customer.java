package com.cg.onlineplantnursery.entity;

import javax.persistence.Column;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userModule")
@Data
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	private String firstName;
	private String lastName;
	private String password;
	private long phoneNumber;
	private String gender;
	private String emailId;
	private String userRole;

	@Embedded
	@Column(nullable = true)

	private Address address;

	

	public Customer(String firstName, String lastName, String password, long phoneNumber, String gender,String emailId,String userRole, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.emailId=emailId;
		this.userRole = userRole;
		this.address = address;
	}

	public Customer(String firstName) {
		super();
		this.firstName = firstName;
	}

	public Customer(String firstName, String lastName, String password, String emailId,String userRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailId = emailId;
		this.userRole = userRole;
	}
	
	


}
