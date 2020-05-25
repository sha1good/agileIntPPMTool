package com.luv2code.ppmtool.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.luv2code.ppmtool.domain.User;
import com.luv2code.ppmtool.services.CustomerUserDetailsService;



import static com.luv2code.ppmtool.security.SecurityConstants.HEADER_STRING;
import static com.luv2code.ppmtool.security.SecurityConstants.TOKEN_PREFIX;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTTokenProvider tokenProvider;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filter)
			throws ServletException, IOException {
		
		try {
			
			  String jwt = getJWTTOKENFromRequest(request);
			  //Revalidate the token here too cos, getJWTTOKENFromRequest can return null
			  
			if(StringUtils.hasText(jwt)&& tokenProvider.validateToken(jwt)) {
				Long userId = tokenProvider.getUserIdFromJWT(jwt);
				
				User userDetails = customerUserDetailsService.loadUserbyId(userId);
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						     new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}  
			  
		} catch (Exception e) {
			logger.error("Could not set the user authentication in Security Context", e);
		}
		
		filter.doFilter(request, response);
	}
	
	private String getJWTTOKENFromRequest(HttpServletRequest request) {
		
		String bearerToken = request.getHeader(HEADER_STRING);
		
		if(StringUtils.hasText(bearerToken)&& bearerToken.startsWith(TOKEN_PREFIX)) {
			 return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
