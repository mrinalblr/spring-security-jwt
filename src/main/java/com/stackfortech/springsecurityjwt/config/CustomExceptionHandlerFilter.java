package com.stackfortech.springsecurityjwt.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackfortech.springsecurityjwt.response.ApiResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
                filterChain.doFilter(httpServletRequest,httpServletResponse);
        }catch(RuntimeException e){
                ApiResponse response = new ApiResponse();
                response.setStatus("failure");
                response.setMessage(e.getLocalizedMessage());
                httpServletResponse.setStatus(401);
                httpServletResponse.getWriter().write(convertObjectToJson(response));

        }
    }
    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
