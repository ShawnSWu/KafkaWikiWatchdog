package io.watchdog.kafka.infra;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.security.protocol}")
    private String securityProtocol;

    @Value("${kafka.sasl.mechanism}")
    private String saslMechanism;

    @Value("${kafka.sasl.jaas.config}")
    private String saslJaasConfig;


    @Value("${kafka.group-id}")
    private String groupId;

    @Bean(name = "kafkaConsumer")
    private KafkaConsumer<String, String> kafkaConsumer(){
        Map<String, Object>  properties = new HashMap<>();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
        properties.put("sasl.mechanism", saslMechanism);
        properties.put("sasl.jaas.config", saslJaasConfig);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        //set safe producer (if kafka <= 2.8
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));

        // create consumer
        return new KafkaConsumer<>(properties);
    }

}
