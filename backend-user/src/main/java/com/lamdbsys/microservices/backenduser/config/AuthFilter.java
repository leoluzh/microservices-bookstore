package com.lamdbsys.microservices.backenduser.config;

import com.lamdbsys.microservices.backenduser.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.AuthFilterConfig> {

    public static final String BEARER = "Bearer";
    public static final String URL_SERVICE_USERS_VALIDATE_TOKEN = "http://service-users/api/v1/users/validatetoken?token=%s";
    public static final String CUSTOM_HEADER_AUTH_USER_ID = "X-auth-user-id" ;
    private final WebClient.Builder webClientBuilder;

    public AuthFilter(WebClient.Builder webClientBuilder){
        super(AuthFilterConfig.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(final AuthFilterConfig authFilterConfig){
        return (exchange, chain) -> {

            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Missing authorization information.");
            }

            final var authHeader = exchange.getRequest().getHeaders()
                    .get(HttpHeaders.AUTHORIZATION).get(0);

            final var parts = authHeader.split(" ");

            if( parts.length != 2 || BEARER.equals(parts[0]) ){
                throw new RuntimeException("Incorrect authorization struct configuration.");
            }

            return webClientBuilder.build()
                    .post()
                    .uri(String.format(URL_SERVICE_USERS_VALIDATE_TOKEN,parts[1]))
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .map( user -> {
                        exchange.getRequest()
                                .mutate()
                                .header(CUSTOM_HEADER_AUTH_USER_ID,String.valueOf(user.getId()));
                        return exchange;
                    }).flatMap(chain::filter);
        };
    }

    public static class AuthFilterConfig {

    }

}
