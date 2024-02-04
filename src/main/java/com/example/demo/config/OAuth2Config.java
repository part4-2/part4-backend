package com.example.demo.config;

import com.example.demo.oauth.repository.InMemoryProviderRepository;
import com.example.demo.oauth.OAuth2Adapter;
import com.example.demo.oauth.OAuth2Provider;
import com.example.demo.oauth.OAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OAuthProperties.class)
@RequiredArgsConstructor
public class OAuth2Config {
    private final OAuthProperties properties;

    @Bean
    public InMemoryProviderRepository inMemoryProviderRepository() {
        Map<String, OAuth2Provider> providers = OAuth2Adapter.getOauthProviders(properties);
        return new InMemoryProviderRepository(providers);
    }
}
