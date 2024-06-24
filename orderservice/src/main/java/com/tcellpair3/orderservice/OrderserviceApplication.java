package com.tcellpair3.orderservice;

import com.turkcell.tcell.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.turkcell.tcell.exception.annotations.EnableException;

@SpringBootApplication
@EnableFeignClients
@EnableSecurity
@EnableException
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}
}
