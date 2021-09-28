package com.example.core.player.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMapperHolder {

    public static final ObjectMapper instance;

    static {
        instance = new ObjectMapper();

        instance.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        instance.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        instance.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        instance.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        instance.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        instance.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
        instance.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        instance.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        instance.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(OffsetDateTime.class, OffsetDateTimeSerializer.INSTANCE);
        instance.registerModule(javaTimeModule);
    }
}
