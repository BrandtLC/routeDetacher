package com.example.demo;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class CustomServletInputStream extends ServletInputStream {
	
	private final InputStream inputStream;

    public CustomServletInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

	 @Override
     public int read() throws IOException {
         return inputStream.read();
     }
	 
	 @Override
     public int available() throws IOException {
         return inputStream.available();
     }
	 
     @Override
     public void close() throws IOException {
         inputStream.close();
     }
     
     @Override
     public synchronized void mark(int readlimit) {
         inputStream.mark(readlimit);
     }
     
     @Override
     public synchronized void reset() throws IOException {
         inputStream.reset();
     }

     @Override
     public boolean markSupported() {
         return inputStream.markSupported();
     }
     
     @Override
     public boolean isFinished() {
         try {
             return inputStream.available() == 0;
         } catch (IOException e) {
             return true;
         }
     }
     
     @Override
     public boolean isReady() {
         return !isFinished();
     }

     @Override
     public void setReadListener(ReadListener listener) {
         throw new UnsupportedOperationException("setReadListener() não implementado.");
     }
}
