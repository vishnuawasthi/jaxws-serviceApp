package com.app.service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.app.dto.UserDTO;
import com.app.exception.UserNotFoundException;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface LoginService {
	
	 UserDTO login(String username, String password) throws UserNotFoundException;
	 
	 boolean addUser(UserDTO userEntity);
	 
	 
	 
}
