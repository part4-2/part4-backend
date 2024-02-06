package com.example.demo.oauth.repository;

import com.example.demo.oauth.OAuth2Provider;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryProviderRepository {
    private final Map<String, OAuth2Provider> providers;

    public InMemoryProviderRepository(Map<String, OAuth2Provider> providers) {
        this.providers = new HashMap<>(providers);
    }

    public OAuth2Provider findByProviderName(String name) {
        return providers.get(name);
    }
}
