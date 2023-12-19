package io.watchdog.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class WikimediaChangesProducer implements CommandLineRunner {

    @Value("${wikimedia.event.change.url}")
    private String url;

    @Value("${kafka.topic.wikimedia}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(String... args) {
        Flux<String> sseFlux = WebClient.create(url).get()
                .uri("/")
                .retrieve()
                .bodyToFlux(String.class)
                .retryWhen(Retry.backoff(Long.MAX_VALUE, Duration.ofSeconds(1))
                        .maxBackoff(Duration.ofSeconds(10))
                        .doAfterRetry(retrySignal -> System.out.println("Retrying retrieve from wikimedia...")));

        sseFlux.subscribe(
                event -> kafkaTemplate.send(topic, event),
                error -> {
                    log.error(error.getMessage());
                },
                () -> {
                }
        );
    }
}
