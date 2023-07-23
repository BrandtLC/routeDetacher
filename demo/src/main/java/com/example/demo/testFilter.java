package com.example.demo;

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
		String route = request.getRequestURI();
		String method = request.getMethod();
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);
		
		jsonMap.put("httpRoute", route);
		jsonMap.put("httpMethod", method);
		
		String json = mapper.writeValueAsString(jsonMap);
		
		HttpServletRequestWrapper requestWrapper = new CustomHttpServletRequestWrapper(request,json);
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/test");
        dispatcher.forward(requestWrapper, response);
	}
	
}
