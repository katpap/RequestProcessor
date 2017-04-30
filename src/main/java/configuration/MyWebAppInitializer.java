package main.java.configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import main.java.entities.Message3;
import main.java.entities.Sale;

public class MyWebAppInitializer implements WebApplicationInitializer {

	    public void onStartup(ServletContext container) {
	      ServletRegistration.Dynamic registration = 
	      container.addServlet("messageServlet", new DispatcherServlet());
	      container.setAttribute("msgCounter", 0);
	      registration.setLoadOnStartup(1);
	      registration.addMapping("/");
	    }
	}
