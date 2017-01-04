package com.poc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	@RequestMapping(value = "/custlist", method = RequestMethod.GET)
	public List<Customer> customerList(){
		List<Customer> c = new ArrayList<Customer>();
		c.add(new Customer("jay1","cainta"));
		c.add(new Customer("jay2","cainta"));
		c.add(new Customer("jay3","cainta"));
		c.add(new Customer("jay4","rizal"));
		c.add(new Customer("jay5","rizal"));
		c.add(new Customer("jay6","rizal"));

		
		return c.stream()
				.filter(p -> p.getAddress().startsWith("r"))
				.collect(Collectors.toList());
	}
}
