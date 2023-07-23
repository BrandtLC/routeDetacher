package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper{
	
	private final byte[] modifiedRequestBody;

	public CustomHttpServletRequestWrapper(HttpServletRequest request, String modifiedRequestBody) {
		super(request);
		this.modifiedRequestBody = modifiedRequestBody.getBytes();
	}
	
	@Override
    public ServletInputStream getInputStream() throws IOException {
        return new CustomServletInputStream(new ByteArrayInputStream(modifiedRequestBody));
    }
	
	@Override
    public String getMethod() {
        return "POST";
    }
	
}
