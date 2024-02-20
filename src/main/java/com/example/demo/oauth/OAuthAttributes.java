package com.example.demo.oauth;

import com.example.demo.user.domain.enums.Provider;

import java.util.Arrays;
import java.util.Map;

public enum OAuthAttributes {
    GOOGLE("google") {
        @Override
        public UserProfile of(Map<String, Object> attributes) {
            return UserProfile.builder()
                    .oauthId(String.valueOf(attributes.get("sub")))
                    .email((String) attributes.get("email"))
                    .imageUrl((String) attributes.get("picture"))
                    .provider(Provider.GOOGLE)
                    .build();
        }
    },
    NAVER("naver") {
        @Override
        public UserProfile of(Map<String, Object> attributes) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            return UserProfile.builder()
                    .oauthId((String) response.get("id"))
                    .email((String) response.get("email"))
                    .imageUrl((String) response.get("profile_image"))
                    .provider(Provider.NAVER)
                    .build();
        }
    },
    KAKAO("kakao") {
        @Override
        public UserProfile of(Map<String, Object> attributes) {

            Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

            return UserProfile.builder()
                    .oauthId(String.valueOf(attributes.get("id")))
                    .email((String) kakaoAccount.get("email"))
                    .imageUrl((String) properties.get("profile_image"))
                    .provider(Provider.KAKAO)
                    .build();
        }
    };

    private final String providerName;

    OAuthAttributes(String providerName) {
        this.providerName = providerName;
    }

    public static UserProfile extract(String providerName, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> providerName.equals(provider.providerName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of(attributes);
    }

    public abstract UserProfile of(Map<String, Object> attributes);

}

