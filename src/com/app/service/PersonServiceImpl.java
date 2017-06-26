package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.app.dto.Person;
import com.app.exception.UserNotFoundException;

@WebService(serviceName = "PersonService", endpointInterface = "com.app.service.PersonService")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class PersonServiceImpl implements PersonService {

	@Resource
	private WebServiceContext ctx;

	private List<Person> list = new ArrayList<Person>();

	public PersonServiceImpl() {
		System.out.println("PersonServiceImpl() - called ");
	}

	@Override
	public void add(Person person) {
		list.add(person);

	}

	@Override
	public Person getPerson(
			@WebParam(name = "userId", header = false) int id,
			@WebParam(name = "HeaderKey", header = true) String HeaderKey,
			@WebParam(name = "username", header = true) String username,
			@WebParam(name = "password", header = true) String password) throws UserNotFoundException {
		MessageContext messageContext = ctx.getMessageContext();
		//String headerVal = (String) messageContext.get("HeaderKey");

		//System.out.println("HeaderVal : "+headerVal);
		System.out.println("HeaderKey :"+HeaderKey);
		
		for (Person person : list) {
			if (person.getId() == id) {
				return person;
			} else {
				throw new UserNotFoundException("Person not found with  given id :" + id);
			}
		}
		return null;
	}

}
