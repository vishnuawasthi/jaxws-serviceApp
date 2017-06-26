package com.app.service;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.app.dto.Person;
import com.app.exception.UserNotFoundException;

@WebService
@HandlerChain(file="handler-chain.xml")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public interface PersonService {

	@WebMethod(operationName = "Add", action = "add")
	void add(@WebParam Person person);

	@WebMethod(operationName = "getPerson", action = "getPerson")
	@WebResult(header = false, partName = "getPersonResponse")
	Person getPerson(
			@WebParam(name = "userId", header = false) int id,
			@WebParam(name = "HeaderKey", header = true) String HeaderKey,
			@WebParam(name = "username", header = true) String username,
			@WebParam(name = "password", header = true) String password) throws UserNotFoundException;

}
