package io.watchdog.kafka.service;

import io.watchdog.opensearch.service.ElasticsearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;

import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Slf4j
@Service
@RequiredArgsConstructor
public class WikiChangeConsumerService implements CommandLineRunner {

    private final ElasticsearchService elasticsearchService;

    private final KafkaConsumer<String, String> kafkaConsumer;

    @Value("${kafka.topic.wikimedia}")
    private String topic;

    @Override
    public void run(String... args) throws Exception {
        setUpShutdownHook();

        if (isFalse(elasticsearchService.isIndexExist())) {
            elasticsearchService.createIndex();
            log.info("The Wikimedia Index has been created!");
        } else {
            log.info("The Wikimedia Index already exits");
        }

        kafkaConsumer.subscribe(Collections.singleton(topic));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(3000));

            int recordCount = records.count();

            log.info("Received " + recordCount + " record(s)");

            int numberOfActions = elasticsearchService.save(records);

            if (numberOfActions < 0) {
                kafkaConsumer.commitSync();
            }
        }
    }

    private void setUpShutdownHook() {
        final Thread mainThread = Thread.currentThread();
        // adding the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Detected a shutdown, let's exit by calling consumer.wakeup()...");
            kafkaConsumer.wakeup();

            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

}