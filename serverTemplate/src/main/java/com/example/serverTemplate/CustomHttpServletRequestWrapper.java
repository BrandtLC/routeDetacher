package com.example.serverTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper{
	
	private final byte[] modifiedRequestBody;
	private final String method;

	public CustomHttpServletRequestWrapper(HttpServletRequest request, String modifiedRequestBody, String method) {
		super(request);
		this.modifiedRequestBody = modifiedRequestBody.getBytes();
		this.method = method;
	}
	
	@Override
    public ServletInputStream getInputStream() throws IOException {
        return new CustomServletInputStream(new ByteArrayInputStream(modifiedRequestBody));
    }
	
	@Override
    public String getMethod() {
        return method;
    }
	
}
