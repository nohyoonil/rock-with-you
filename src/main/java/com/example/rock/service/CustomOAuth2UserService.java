package com.example.rock.service;

import com.example.rock.entity.Member;
import com.example.rock.oauth.model.CustomOauth2User;
import com.example.rock.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>, UserDetailsService {

    private final MemberRepository memberRepository;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);
            Map<String, Object> attributes = oAuth2User.getAttributes();

            Long socialId = ((Number) attributes.get("id")).longValue();
            Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
            String nickname = properties.get("nickname").toString();
            String pfp = properties.get("profile_image").toString();

            Member member = memberRepository.findById(socialId).orElseGet(() ->
                    memberRepository.save(Member.builder()
                            .id(socialId)
                            .name(nickname)
                            .pfp(pfp)
                            .createdAt(LocalDateTime.now())
                            .build()));

            return new CustomOauth2User(member.getId(), attributes);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        if (!memberRepository.existsById(Long.valueOf(id)))
            throw new UsernameNotFoundException("User not found");

        return new User(id, "", List.of());
    }
}
