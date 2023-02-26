package com.cg.onlineplantnursery.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.cg.onlineplantnursery.entity.Customer;
import com.cg.onlineplantnursery.exceptions.InvalidCustomerException;

@Service
public interface ICustomerService {

	public Customer registerUser(Customer user)throws Exception;
	public List<Customer> getAllUsers();
	public Customer getUserById(int userId);
	public Customer updateUser(int userId);
	public List<Customer> getUserByState(String state);
	public List<Customer> getUserByCity (String city);
	

}
