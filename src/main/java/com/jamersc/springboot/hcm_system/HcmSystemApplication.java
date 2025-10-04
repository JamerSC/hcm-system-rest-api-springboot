package com.jamersc.springboot.hcm_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class HcmSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcmSystemApplication.class, args);
		//System.out.println("Hello, world!");
	}

}
