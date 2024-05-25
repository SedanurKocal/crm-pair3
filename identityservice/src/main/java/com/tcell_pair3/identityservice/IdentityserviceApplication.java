package com.tcell_pair3.identityservice;

import com.turkcell.tcell.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSecurity
public class IdentityserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityserviceApplication.class, args);
	}

}
