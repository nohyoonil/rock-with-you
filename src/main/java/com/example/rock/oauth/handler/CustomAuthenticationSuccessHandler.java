package com.example.rock.oauth.handler;

import com.example.rock.jwt.JwtUtil;
import com.example.rock.oauth.model.CustomOauth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOauth2User member = (CustomOauth2User) authentication.getPrincipal();
        Map<String, Object> attributes = member.getAttributes();
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        String socialId = String.valueOf(attributes.get("id")); // 카카오 고유 id
        String name = (String) properties.get("nickname");
        String pfp = (String) properties.get("profile_image");
        String accessToken = jwtUtil.generateToken(socialId, name, pfp, Duration.ofDays(1));

        Cookie cookie = new Cookie("Authorization", accessToken);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);

        response.sendRedirect("/");
    }
}
