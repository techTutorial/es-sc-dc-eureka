package example.spring.cloud.eureka.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import example.spring.cloud.eureka.service.GreetingsService;

@Service
public class GreetingsServiceImpl implements GreetingsService {
	
	static Map<String, String> greetingsMap;
	// Initialize map at application start-up
	static {
		greetingsMap = new HashMap<String, String>();
		greetingsMap.put("fr", "BONJOUR");
		greetingsMap.put("es", "HOLA");
		greetingsMap.put("de", "GUTENTAG");
		greetingsMap.put("it", "CIAO");
		greetingsMap.put("hi", "नमस्ते");
		greetingsMap.put("en", "GOOD MORNING");
	}
	
	
	@Value("${eureka.instance.instance-id}")
	private String instanceId;
	
	
	@Override
	public String getGreetings(String langCode) {
		System.out.println("Fetching greetings type for locale id= " + langCode);
		String msg = greetingsMap.entrySet().stream().filter((lId) -> langCode.equalsIgnoreCase(lId.getKey()))
				.map(langName -> langName.getValue()).collect(Collectors.joining());
		
		// Useful to verify multiple instances of micro-service by clicking on refresh button of browser
		String msgInst = msg + " using instance id " + instanceId;
		
		return msgInst;
	}

}
