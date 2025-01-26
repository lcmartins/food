package com.lcm.food.adapters.driven.persistence.resilience;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcm.food.application.domain.entities.Order;
import com.lcm.food.application.ports.driven.IResiliencePort;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaAdapter implements IResiliencePort {
    @Override
    public void save(Order order) throws ExecutionException, InterruptedException, JsonProcessingException {
        KafkaProducer<String, String> producer = createProducer();
        ProducerRecord<String, String> record = new ProducerRecord<>("teste", "transferencia", new ObjectMapper().writeValueAsString(order));
        Callback callback = (data, ex) -> {
            if(Objects.nonNull(ex)) {
                ex.printStackTrace();
                return;
            }
            System.out.println(String.format("mensagem enviada com sucesso para o topico: %s e particao: %d", data.topic(), data.partition()));
        };
        producer.send(record, callback).get();
    }

    private KafkaProducer<String, String> createProducer() {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        props.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<String, String>(props);
    }
}
