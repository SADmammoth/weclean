package com.example.weclean;

import com.example.weclean.service.VacuumCleanerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class WecleanApplication{

	@Autowired
	private VacuumCleanerService vacuumCleanerService;

	public static void main(String[] args) {
		SpringApplication.run(WecleanApplication.class, args);
	}



}
