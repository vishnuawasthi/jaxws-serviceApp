package com.app.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

public class ServiceValidationHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext soapMessageContext) {
		System.out.println("handleMessage() - start");

		// Standard property: message direction, true for outbound messages,
		// false for inbound.
		Boolean isInboundReq = (Boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!isInboundReq) {

			try {
				SOAPMessage soapMessage = soapMessageContext.getMessage();
				SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnvelope.getHeader();

				if (null != soapHeader) {

					Iterator itr = soapHeader.getChildElements();

					while (itr.hasNext()) {

						// String str = (String) itr.next();

						SOAPHeaderElement element = (SOAPHeaderElement) itr.next();

						String usernameStr = element.getAttribute("username");
						String passswordStr = element.getAttribute("password");

						System.out.println("usernameStr   : " + usernameStr);
						System.out.println("passswordStr   : " + passswordStr);

					}

					String username = soapHeader.getAttribute("username");
					String password = soapHeader.getAttribute("password");
					System.out.println("Username : " + username);
					System.out.println("password : " + password);
					// Validation logic goes here
					if ("Admin".equals(username) && "root".equals(password)) {
						// Validation success flow
						System.out.println("Validation Success flow..");
						return true;
					} else {
						generateSOAPErrorMessage(soapMessage, "Authentication failed.");
					}

				} else {
					soapHeader = soapEnvelope.addHeader();
					generateSOAPErrorMessage(soapMessage, "Missing required headers.");
				}

			} catch (SOAPException e) {
				e.printStackTrace();
			}

		}
		System.out.println("handleMessage() - end");
		return false;
	}

	@Override
	public void close(MessageContext arg0) {
		System.out.println("close() - start");

		System.out.println("close() - end");

	}

	private void generateSOAPErrorMessage(SOAPMessage soapMessage, String reasonString) {
		try {
			SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
			SOAPBody soapBody = soapEnvelope.getBody();
			SOAPFault soapFault = soapBody.addFault();
			soapFault.setFaultString(reasonString);
			soapFault.setFaultCode("Client");
			throw new SOAPFaultException(soapFault);
		} catch (SOAPException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		System.out.println("handleFault() - start");

		System.out.println("handleFault() - end");
		return false;
	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("getHeaders() - start");

		System.out.println("getHeaders() - end");
		return null;
	}

}
