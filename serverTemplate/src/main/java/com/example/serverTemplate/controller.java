package com.example.serverTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class controller {
	
	
	@PostMapping("/1")
	public ResponseEntity<String> test(@RequestBody String token) {
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

}
