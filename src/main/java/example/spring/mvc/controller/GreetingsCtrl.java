package example.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.spring.mvc.service.impl.GreetingsServiceImpl;

@RestController
@RequestMapping(value = "/greet")
public class GreetingsCtrl {
	
	@Autowired
	GreetingsServiceImpl greetService;

	@GetMapping(value = "/msg/{localeId}")
	public String getGreetings(@PathVariable(name = "localeId") String langCode) {
		return greetService.getGreetings(langCode);
	}

}
