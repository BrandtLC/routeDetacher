package com.example.serverTemplate;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class testFilter extends OncePerRequestFilter{

	@SuppressWarnings("unchecked")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		ServletInputStream inputStream = request.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);
		
		String route = (String) jsonMap.get("httpRoute");
		String method = (String) jsonMap.get("httpMethod");
		System.out.println(method);
		
		
		jsonMap.remove("httpRoute");
		jsonMap.remove("httpMethod");
		
		String json = mapper.writeValueAsString(jsonMap);
		
		HttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(request, json, method);
		
        RequestDispatcher dispatcher = request.getRequestDispatcher(route);
        dispatcher.forward(requestWrapper, response);
	}
	
}
