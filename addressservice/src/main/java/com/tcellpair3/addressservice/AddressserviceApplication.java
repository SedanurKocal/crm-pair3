package com.tcellpair3.addressservice;

import com.turkcell.tcell.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.turkcell.tcell.exception.annotations.EnableException;

@SpringBootApplication
@EnableFeignClients
@EnableSecurity
@EnableException

public class AddressserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressserviceApplication.class, args);
	}

}
