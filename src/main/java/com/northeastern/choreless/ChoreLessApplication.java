package com.northeastern.choreless;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//(scanBasePackages = {"com.northeastern.controller"})
@SpringBootApplication(scanBasePackages = {"com.northeastern"})
public class ChoreLessApplication {

	public static void main(String[] args) {
		TimeZone tz = TimeZone.getDefault();
        String id = tz.getID();
        System.out.println("The server's default timezone is: " + id);
		SpringApplication.run(ChoreLessApplication.class, args);
	}

}	
