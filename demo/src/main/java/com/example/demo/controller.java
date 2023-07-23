package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.http.HttpFeignClient;

@Controller
@RequestMapping("/test")
public class controller {
	@Autowired
	HttpFeignClient client;
	
	@GetMapping
	public void test() {
		Token token = new Token("test"); 
	}
	
	@PostMapping
	public ResponseEntity<String> test(@RequestBody String token) {
		String response = client.send(token);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
