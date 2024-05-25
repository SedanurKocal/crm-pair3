package com.tcellpair3.addressservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AddressserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressserviceApplication.class, args);
	}

}
