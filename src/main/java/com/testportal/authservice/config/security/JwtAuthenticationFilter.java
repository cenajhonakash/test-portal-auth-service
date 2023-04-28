//package com.testportal.authservice.config.security;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.testportal.authservice.config.CustomUserDetails;
//import com.testportal.authservice.config.CustomUserDetailsService;
//import com.testportal.authservice.constants.AppConstants;
//import com.testportal.authservice.dto.CredentialsDto;
//import com.testportal.authservice.helper.JwtHelper;
//
//import io.jsonwebtoken.ExpiredJwtException;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//	private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//
//	@Autowired
//	private CustomUserDetailsService customUserDetailsService;
//	@Autowired
//	private JwtHelper jwtHelper;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		log.info("doFilterInternal() invoked");
//		final String header = request.getHeader(AppConstants.Authorization.name());
//
//		String username = null, jwtToken = null;
//
//		if (header != null && header.startsWith(AppConstants.Bearer.name())) {
//			jwtToken = header.substring(7);
//			try {
//				username = jwtHelper.extractUsername(jwtToken);
//				// userName = this.jwtutil.extractUsername(jwtToken);
//			} catch (ExpiredJwtException e) {
//				log.error("error caught while extracting user: {}", e.getMessage());
//			} catch (Exception e) {
//				log.error("error caught while extracting user: {}", e.getMessage());
//			}
//
//		} else {
//			log.error("Invalid token: {} ", jwtToken);
//		}
//
//		// user is validated
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			log.info("setting authentication first time for user: {}", username);
//
//			CustomUserDetails userdetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
//			if (jwtHelper.validateToken(jwtToken, CredentialsDto.builder().username(userdetails.getUsername()).build(), null)) {
//
//				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
//				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//				SecurityContextHolder.getContext().setAuthentication(authToken);
//			} else {
//				log.error("Invalid token: {}", jwtToken);
//			}
//		}
//
//		filterChain.doFilter(request, response);
//	}
//
//}
