package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.example.demo.entity.UserBean;
import com.example.demo.service.TokenService;

@Component
public class JWTFilter extends GenericFilterBean {

	 @Autowired
	    private TokenService userDetailsService;

	    @Autowired
	    private JwtUtil jwtUtil;
	    
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
System.out.println("hiiii");
		final HttpServletRequest request = (HttpServletRequest) req;
		final String authorization = request.getHeader("authorization");

		if (authorization == null || !authorization.startsWith("Bearer ")) {
			System.out.println("if---");
			throw new ServletException("401 - UNAUTHORIZED");
		}
//		try {
//			final Claims claims = Jwts.parser().setSigningKey("123#&*zcvAWEE999").parseClaimsJws(authorization.substring(7))
//					.getBody();
//			request.setAttribute("claims", claims);
//		} catch (final SignatureException e) {
//			System.out.println("catch -----");
//			throw new ServletException("401 - UNAUTHORIZED");
//		}
		
		String username = null;
        String jwt = null;

        if (authorization != null && authorization.startsWith("Bearer ")) {
            jwt = authorization.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserBean userDetails =this.userDetailsService.getToken(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null);
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

		chain.doFilter(req, res);
	}
}