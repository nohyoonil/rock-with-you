package com.example.rock.service;

import com.example.rock.entity.Member;
import com.example.rock.oauth.model.CustomOauth2User;
import com.example.rock.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        Long socialId = Long.valueOf(String.valueOf(attributes.get("id"))); // 카카오 고유 id
        String name = (String) profile.get("nickname");
        String pfp = (String) kakaoAccount.get("profile_image");

        Member member = memberRepository.findById(socialId).orElseGet(() ->
                memberRepository.save(Member.builder()
                        .id(socialId)
                        .name(name)
                        .pfp(pfp)
                        .createdAt(LocalDateTime.now())
                        .build()));

        return new CustomOauth2User(member, attributes);
    }
}
