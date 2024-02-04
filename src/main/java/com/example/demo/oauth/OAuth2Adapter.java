package com.example.demo.oauth;

import java.util.HashMap;
import java.util.Map;

public class OAuth2Adapter {
    private OAuth2Adapter() {}

    public static Map<String, OAuth2Provider> getOauthProviders(OAuthProperties properties) {
        Map<String, OAuth2Provider> oauthProvider = new HashMap<>();

        properties.getUser().forEach((key, value) -> oauthProvider.put(key,
                new OAuth2Provider(value, properties.getProvider().get(key))));
        return oauthProvider;
    }
}
