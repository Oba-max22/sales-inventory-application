package com.obamax.model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.kafka.common.serialization.Serializer;

public class OrderSerializer implements Serializer<Order> {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);

    @Override
    public byte[] serialize(String topic, Order order) {
        byte[] result = null;
        try {
            result = objectMapper.writeValueAsBytes(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}