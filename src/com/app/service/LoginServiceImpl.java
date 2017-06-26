package com.app.service;

import javax.jws.WebService;

import com.app.dto.UserDTO;
import com.app.exception.UserNotFoundException;

@WebService(serviceName = "LoginService", endpointInterface = "com.app.service.LoginService")
public class LoginServiceImpl implements LoginService {

	UserDTO[] users = new UserDTO[100];

	private UserDTO[] getAllUsers() {
		// UserDTO(Long id, String firstName, String lastName, String
		// username,String password)
		UserDTO userDTO = new UserDTO(10L, "Vishnu", "Awasthi", "admin", "root");
		UserDTO userDTO2 = new UserDTO(11L, "Jay", "Jay", "user", "test");
		users[0] = userDTO;
		users[1] = userDTO2;
		return users;

	}

	@Override
	public UserDTO login(String username, String password) throws UserNotFoundException {

		for (UserDTO userDTO : users) {

			if (userDTO.getUsername().equals(username) && userDTO.getPassword().equals(password)) {
				return userDTO;

			} else {
				throw new UserNotFoundException("We are not able to find you in our database");
			}

		}
		return null;
	}

	@Override
	public boolean addUser(UserDTO userEntity) {
		int size = users.length;
		if (size > 100) {
			users[size+1] = userEntity;
			return true;
		}
		return false;
	}

	/*public static void main(String args[]) {

		int[] numbers = { 10, 20, 30 };

		System.out.println("length : " + numbers.length);

	}*/

}
