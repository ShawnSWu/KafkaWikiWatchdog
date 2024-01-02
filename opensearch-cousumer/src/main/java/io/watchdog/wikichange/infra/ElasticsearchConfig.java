package io.watchdog.wikichange.infra;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Value("${open-search.url}")
    private String hostname;

    @Value("${open-search.port}")
    private String port;

    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(hostname, Integer.parseInt(port), "http"));
        return new RestHighLevelClient(builder);
    }

}