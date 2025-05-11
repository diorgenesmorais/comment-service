package com.dms.dmscomments.comment.service.api.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestClientFactory {

    private final RestClient.Builder builder;

    public RestClient moderationClient() {
        return builder
                .requestFactory(generateClientHttpRequestFactory())
                .baseUrl("http://localhost:8081")
                .defaultStatusHandler(HttpStatusCode::isError, (req, resp) -> {
                    log.error("Erro ao chamar serviço de moderação");
                    throw new ModerationClientBadGatewayException("Erro ao chamar serviço de moderação");
                })
                .build();
    }

    private ClientHttpRequestFactory generateClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(Duration.ofSeconds(5));
        factory.setConnectTimeout(Duration.ofSeconds(3));

        return factory;
    }
}
