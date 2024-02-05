package com.example.demo.oauth.service;

import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.oauth.*;
import com.example.demo.oauth.repository.InMemoryProviderRepository;
import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2Service {

    private final InMemoryProviderRepository inMemoryProviderRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(String providerName, String code) {

        OAuth2Provider provider = inMemoryProviderRepository.findByProviderName(providerName);

        OAuthTokenResponse tokenResponse = getToken(code, provider);

        UserProfile userProfile = getUserProfile(providerName, tokenResponse, provider);

        Users user = saveOrUpdate(userProfile);

        String accessToken = jwtTokenProvider.createToken(user.getEmail());


        return LoginResponse.builder()
                .id(user.getId())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .role(user.getRole())
                .tokenType("Bearer")
                .accessToken(accessToken)
                .build();
    }


    private OAuthTokenResponse getToken(String code, OAuth2Provider provider) {
        return WebClient.create()
                .post()
                .uri(provider.getTokenUrl())
                .headers(header -> {
                    header.setBasicAuth(provider.getClientId(), provider.getClientSecret());
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                })
                .bodyValue(tokenRequest(code, provider))
                .retrieve()
                .bodyToMono(OAuthTokenResponse.class)
                .block();
    }

    private MultiValueMap<String, String> tokenRequest(String code, OAuth2Provider provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", provider.getClientId());
        formData.add("redirect_uri", provider.getRedirectUrl());
        formData.add("code", code);
        formData.add("client_secret", provider.getClientSecret());

        return formData;
    }

    private UserProfile getUserProfile(String providerName, OAuthTokenResponse tokenResponse, OAuth2Provider provider) {
        Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponse);
        return OAuthAttributes.extract(providerName, userAttributes);
    }


    private Map<String, Object> getUserAttributes(OAuth2Provider provider, OAuthTokenResponse tokenResponse) {
        return WebClient.create()
                .get()
                .uri(provider.getUserInfoUrl())
                .headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();
    }


    private Users saveOrUpdate(UserProfile userProfile) {
        Users user = userRepository.findByOauthId(userProfile.getOauthId())
                .map(entity -> entity.update(
                        userProfile.getEmail(), userProfile.getName(), userProfile.getImageUrl()))
                .orElseGet(userProfile::toEntity);
        return userRepository.save(user);
    }

}
