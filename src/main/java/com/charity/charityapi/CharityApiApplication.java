package com.charity.charityapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = {"http://localhost:3000"})
public class CharityApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CharityApiApplication.class, args);
  }

}
