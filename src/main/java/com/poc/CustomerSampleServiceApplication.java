package com.poc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.model.Customer;

@EnableDiscoveryClient
@SpringBootApplication
public class CustomerSampleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerSampleServiceApplication.class, args);
	}
}

@RequestMapping("/customer")
@RefreshScope
@RestController
class CustomerService{
	
	@Value("${name}")
	String name;
	
	@Value("${address}")
	String address;
	
	@RequestMapping(value = "/getinfo", method = RequestMethod.GET)
	public Customer getCustomerInfo(){
		return new Customer(name,address);
	}
	
	@RequestMapping(value = "/postinfo", method = RequestMethod.POST)
	public Customer postCustomerInfo(@RequestBody Customer customer){
		return customer;
	}
}
