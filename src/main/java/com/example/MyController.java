package com.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@Value("${my.name:default}")
    private String name;
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
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
		return InetAddress.getLocalHost().getHostName();
	}

}
