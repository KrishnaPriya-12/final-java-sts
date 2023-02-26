package com.cg.onlineplantnursery.service;

import java.util.ArrayList;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineplantnursery.entity.Customer;
import com.cg.onlineplantnursery.repository.ICustomerRepository;
import com.cg.onlineplantnursery.exceptions.InvalidCustomerException;


@Service

public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	ICustomerRepository customerRepository;

	@Override
	public Customer registerUser(Customer user)throws InvalidCustomerException {
	
		if(user.getFirstName().equals("") || user.getLastName().equals("")) {
			throw new InvalidCustomerException("User name","First Name or Last Name is null");
		}
		
		if(user.getEmailId().equals("")) {
			throw new InvalidCustomerException("Email Id","Email Id cannot be null");
		}
		if(user.getPassword().equals("")) {
			throw new InvalidCustomerException("Password","Password cannot be null");
		}
		return customerRepository.save(user);
	}

	@Override
	public List<Customer> getAllUsers() {

		return customerRepository.findAll();
	}

	
	@Override
	public List<Customer> getUserByCity(String searchCity) {
		//get all users 
		List<Customer> allUsers = customerRepository.findAll();
		//Filter user through for loop based on city
		List<Customer> allUserByCity = new ArrayList<>();
		for(Customer user : allUsers) {
			String userCity =user.getAddress().getCity();
		if(userCity.equals(searchCity)) {
			allUserByCity.add(user);
		}
		}
		
		return allUserByCity;
	}
	
	

	@Override
	public List<Customer> getUserByState(String searchstate) {
		
		List<Customer> allUsers = customerRepository.findAll();
		List<Customer> allUserByState = new ArrayList<>();
		for (Customer user: allUsers) {
			String userState = user.getAddress().getState();
			if(userState.equals(searchstate)) {
				allUserByState.add(user);
			}
		}
		return allUserByState;
	}

	@Override
	public Customer getUserById(int userId) {
		
		Customer userFromDB = customerRepository.getReferenceById(userId);
		customerRepository.getReferenceById(userId);
	
		return userFromDB;
	}

	@Override
	public Customer updateUser(int userId) {
	
		Customer updatedUser = customerRepository.getReferenceById(userId);
		customerRepository.save(updatedUser);
		return updatedUser;
	}

	

}
