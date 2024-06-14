package com.tcellpair3.addressservice;

import com.turkcell.tcell.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableSecurity


public class AddressserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressserviceApplication.class, args);
	}

}
