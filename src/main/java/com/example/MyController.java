package com.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {
	
	RestTemplate rt = new RestTemplate();
	
	@Value("${my.name:default}")
    private String name;
	
	@Value("${myService:myservice}")
    private String myService;
	
	@RequestMapping("/")
	public Response home() throws UnknownHostException {
		return new Response().withMessage(String.format("Welcome to '%s'", InetAddress.getLocalHost().getHostName()));
	}
		
	@RequestMapping("/call")
	public Response call() throws Exception {
		return new Response().withMessage(String.format("'%s' calls '%s'", InetAddress.getLocalHost().getHostName()
				, this.rt.getForEntity(String.format("http://%s/host", myService), String.class).getBody()));
	}
	
	@RequestMapping("/hello")
	public Response hello() {
		return new Response().withMessage("hello");
	}
	
	@RequestMapping("/foo")
	public String foo() {
		throw new IllegalArgumentException("Server error");
	}
	
	@RequestMapping("/name")
	public String getName() {
		return this.name;
	}
	
	@RequestMapping("/host")
	public String getHost() throws UnknownHostException {
		return String.format("%s",InetAddress.getLocalHost().getHostName());
	}

}
