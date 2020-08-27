package com.safetynet.alerts.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AlertsFilter extends OncePerRequestFilter{
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		ContentCachingResponseWrapper responseWrapper =new ContentCachingResponseWrapper(response);
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
       
       
		try{
			filterChain.doFilter(requestWrapper, responseWrapper);
			logger.info(">>> Ip adress of the client : " + requestWrapper.getLocalAddr());
		}finally {
			logger.info(">>> HTTP Response Status : " + responseWrapper.getStatus());
			
			
			byte[] responseArray = responseWrapper.getContentAsByteArray();
            String responseStr = new String(responseArray,responseWrapper.getCharacterEncoding());
            
			logger.info(">>> HTTP Response Body : " + responseStr);
			responseWrapper.copyBodyToResponse();
			
		}
	}
	

}
