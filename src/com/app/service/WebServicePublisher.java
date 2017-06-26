package com.app.service;

import javax.xml.ws.Endpoint;

public class WebServicePublisher {

	public static void main(String[] args) {
		// Endpoint.publish("http://localhost:8888/ws/user", new LoginServiceImpl());  
		
		Endpoint.publish("http://localhost:8888/ws/person", new PersonServiceImpl());

	}

}
