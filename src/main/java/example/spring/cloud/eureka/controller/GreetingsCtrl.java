package example.spring.cloud.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.spring.cloud.eureka.service.GreetingsService;

@RestController
@RequestMapping(value = "/greet")
public class GreetingsCtrl {
	
	@Autowired
	GreetingsService greetService;

	// http://localhost:8181/greet/msg/en
	@GetMapping(value = "/msg/{localeId}")
	public String getGreetings(@PathVariable(name = "localeId") String langCode) {
		return greetService.getGreetings(langCode);
	}

}
