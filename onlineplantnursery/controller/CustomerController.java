package com.cg.onlineplantnursery.controller;

import java.util.ArrayList;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineplantnursery.entity.Customer;
import com.cg.onlineplantnursery.service.ICustomerService;
import com.cg.onlineplantnursery.dto.CustomerDTO;
import com.cg.onlineplantnursery.util.CustomerDTOConverter;



@RestController
@RequestMapping("/customer")
@CrossOrigin(origins= {"http://localhost:4200","http://localhost:6001"},allowedHeaders="*")

public class CustomerController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ICustomerService userService;

	@Autowired
	CustomerDTOConverter dtoConvertor;

	public CustomerController() {
		logger.info("Customer Controller Called");
		System.err.println("Customer Controller Called");

	}
	@PostMapping("/add")

	public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody Customer user) throws Exception {

		Customer savedCustomer = userService.registerUser(user);
		logger.info("----> Customer Saved <----" + savedCustomer);

		CustomerDTO dto = dtoConvertor.convertTo(savedCustomer);

		return new ResponseEntity<CustomerDTO>(dto, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<CustomerDTO>> getAllUsers() {

		List<Customer> allUsersInDB = userService.getAllUsers();

		List<CustomerDTO> dtoList = new ArrayList<>();
		for (Customer user : allUsersInDB) {

			CustomerDTO dto = dtoConvertor.convertTo(user);
			dtoList.add(dto);
		}

		return new ResponseEntity<List<CustomerDTO>>(dtoList, HttpStatus.OK);
	}
	
	@GetMapping("/login/{uId}/{password}")
	public ResponseEntity<CustomerDTO> doLogin( @PathVariable int uId,@PathVariable String password)
	{
		Customer userFromDB = userService.getUserById(uId);
		System.err.println(userFromDB);
		if (userFromDB != null) {
			if(userFromDB.getPassword().equals(password)) {
				System.err.println("inside if");
				CustomerDTO dto = dtoConvertor.convertTo(userFromDB);
				return new ResponseEntity<CustomerDTO>(dto, HttpStatus.OK);
			}
			return null;
		} else
			return null;
	}
	
	

	@GetMapping("/userbycity/{city}")

	public ResponseEntity<List<CustomerDTO>> getUserByCity(@PathVariable String city) {

		List<Customer> allUsers = userService.getUserByCity(city);
		List<CustomerDTO> dto = new ArrayList<>();
		for(Customer user: allUsers) {
			CustomerDTO userDTO = dtoConvertor.convertTo(user);
			dto.add(userDTO);
		}

		return new ResponseEntity<List<CustomerDTO>>(dto, HttpStatus.OK);

	}

	@GetMapping("/userbystate/{state}")

	public ResponseEntity<List<CustomerDTO>> getUserByState(@PathVariable String state) {

		List<Customer> allUsers = userService.getUserByState(state);
		List<CustomerDTO> dtoObj= new ArrayList<>();
		for(Customer user: allUsers) {
			CustomerDTO userDTO = dtoConvertor.convertTo(user);
			dtoObj.add(userDTO);
		}

		return new ResponseEntity<List<CustomerDTO>>(dtoObj, HttpStatus.OK);
	
		
	}

	@GetMapping("/userbyid/{userId}")

	public ResponseEntity<CustomerDTO> getUserById(@PathVariable int userId) throws Exception {
		Customer userFromDB = userService.getUserById(userId);
		if (userFromDB != null) {
			CustomerDTO dto = dtoConvertor.convertTo(userFromDB);
			return new ResponseEntity<CustomerDTO>(dto, HttpStatus.OK);
		} else
			return null;
	}

	@PutMapping("/updateuser/{userId}")

	public String updatedUser(@PathVariable int userId) {

		Customer updatedUser = userService.getUserById(userId);
		return updatedUser.toString();
	}

}
//User Module