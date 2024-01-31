package com.example.demo.oauth;

import com.example.demo.domain.entity.Provider;
import com.example.demo.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Builder
@Slf4j
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private Provider provider;

    public static OAuthAttributes of(String registrationId, String attributeKey, Map<String, Object> attributes) {
        return switch (registrationId) {
            case "google" -> ofGoogle(attributeKey, attributes);
            case "kakao" -> ofKakao(attributeKey, attributes);
            case "naver" -> ofNaver("id", attributes);
            default -> throw new RuntimeException("제공되지 않는 서비스 입니다.");
        };
    }

    private static OAuthAttributes ofGoogle(String attributeKey, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .email(String.valueOf(attributes.get("email")))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .provider(Provider.GOOGLE)
                .build();
    }

    private static OAuthAttributes ofKakao(String attributeKey , Map<String,Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

        return OAuthAttributes.builder()
                .email(String.valueOf(kakaoAccount.get("email")))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .provider(Provider.KAKAO)
                .build();
    }

    private static OAuthAttributes ofNaver(String attributeKey, Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .email(String.valueOf(response.get("email")))
                .attributes(response)
                .attributeKey(attributeKey)
                .provider(Provider.NAVER)
                .build();
    }



    public Users toEntity() {
        return Users.builder()
                .email(email)
                .provider(provider)
                .build();
    }

}
