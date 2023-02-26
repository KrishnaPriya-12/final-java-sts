package com.cg.onlineplantnursery.util;

import org.springframework.stereotype.Component;


import com.cg.onlineplantnursery.entity.Customer;
import com.cg.onlineplantnursery.dto.CustomerDTO;



@Component

public class CustomerDTOConverter {
	
	public CustomerDTO convertTo(Customer user) {

		return new CustomerDTO(user.getUserId(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(),
				user.getEmailId(),user.getUserRole());
	}

}
