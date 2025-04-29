package com.example.rock.jwt;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilter {

    private final JwtUtil jwtUtil;
    private final String AUTHORIZATION_HEADER = "Authorization";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwtToken = getTokenFromCookie(httpServletRequest);

        if(StringUtils.hasText(jwtToken) && jwtUtil.validToken(jwtToken)) {
            Authentication authentication = jwtUtil.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private String getTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null; //Cannot read the array length because "<local4>" is null 에러 해결
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(AUTHORIZATION_HEADER)) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }
}
