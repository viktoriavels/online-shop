package ru.vels.orders.client;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;

import java.util.Optional;

@RequiredArgsConstructor
public class FeignClientConfiguration {

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template ->
                template.header("Authorization", "Bearer " + getAccessToken());
    }

    private String getAccessToken() {
        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId("keycloak")
                .principal("keycloak")
                .build();
        return Optional.ofNullable(oAuth2AuthorizedClientManager.authorize(request))
                .map(OAuth2AuthorizedClient::getAccessToken)
                .map(AbstractOAuth2Token::getTokenValue)
                .orElse("");
    }
}