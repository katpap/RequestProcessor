package main.java.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.MessageProcessor;
import main.java.entities.Message1;
import main.java.entities.Message2;
import main.java.entities.Message3;

///The limitation mentioned in the Technical Test Description regarding the minimum external jars
//was quite vague. As external jars someone could think of any jar apart from the 
//jars included in the jdk and server(in my case Tomcat) used. 
//Springframework jars could be considered as external, so it would be very helpful if
//you had elaborated more on the limitations regarding the implementation.
//In that case, a creation of a class extending HTTPServlet,
//registration of the servlet and overriding doPost method would be required.
//I've decided to go go with Spring Rest Services and to consider springframework jars 
//as basic jars since it is my choice of framework.
//This application is deployed in apache-tomcat-8.5.9, with jdk7(1.7.0)

@Controller
@RequestMapping("/sale")
public class MessageController {

	 @Autowired
	 private ServletContext context;

	// spring deserializes/serializes strings to objects, I prefer to use
	// objects in
	// my implementation when possible to limit syntactically incorrect
	// requests.
	@RequestMapping(value = "/add1", method = RequestMethod.POST, headers = "Accept=application/json, application/xml")
	public @ResponseBody
	String add(@RequestBody Message1 message, HttpServletResponse rs) {

		if(!MessageProcessor.checkCounter(context)){
			rs.setStatus(HttpStatus.LOCKED.value());
			return "Your request could not be processed. Please retry later";
		}


		if (!message.isValid()) {
			rs.setStatus(HttpStatus.BAD_REQUEST.value());
			return "";
		}

		MessageProcessor.process(message,  (int)context.getAttribute("msgCounter"));

		rs.setStatus(HttpStatus.OK.value());
		return "OK";
	}

	@RequestMapping(value = "/add2", method = RequestMethod.POST, headers = "Accept=application/json, application/xml")
	public @ResponseBody
	String add(@RequestBody Message2 message, HttpServletResponse rs) {

		if(!MessageProcessor.checkCounter(context)){
			rs.setStatus(HttpStatus.LOCKED.value());
			return "Your request could not be processed. Please retry later";
		}

		if (!message.isValid()) {
			rs.setStatus(HttpStatus.BAD_REQUEST.value());
			return "";
		}

		MessageProcessor.process(message,  (int)context.getAttribute("msgCounter"));

		rs.setStatus(HttpStatus.OK.value());
		return "OK";
	}

	@RequestMapping(value = "/add3", method = RequestMethod.POST, headers = "Accept=application/json, application/xml")
	public @ResponseBody
	String add(@RequestBody Message3 message, HttpServletResponse rs) {

		if(!MessageProcessor.checkCounter(context)){
			rs.setStatus(HttpStatus.LOCKED.value());
			return "Your request could not be processed. Please retry later";
		}

		if (!message.isValid()) {
			rs.setStatus(HttpStatus.BAD_REQUEST.value());
			return "";
		}

		MessageProcessor.process(message,  (int)context.getAttribute("msgCounter"));

		rs.setStatus(HttpStatus.OK.value());
		return "OK";
	}

}
