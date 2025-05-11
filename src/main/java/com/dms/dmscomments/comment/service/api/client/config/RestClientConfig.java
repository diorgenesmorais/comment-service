package com.dms.dmscomments.comment.service.api.client.config;

import com.dms.dmscomments.comment.service.api.client.ModerationClient;
import com.dms.dmscomments.comment.service.api.client.RestClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Bean
    ModerationClient moderationClient(RestClientFactory factory) {
        RestClient restClient = factory.moderationClient();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();

        return proxyFactory.createClient(ModerationClient.class);
    }
}
