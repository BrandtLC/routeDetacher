package com.example.demo.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "test", url = "http://localhost:8082/")
public interface HttpFeignClient {
	@PostMapping
	String send(String payload);
}
