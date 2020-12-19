package net.tirasa.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

class YAMLTest extends SerializationTest {

    private static final YAMLMapper YAML_MAPPER;

    static {
        YAML_MAPPER = new YAMLMapper();
        YAML_MAPPER.registerModule(new AfterburnerModule());
        YAML_MAPPER.registerModule(new JavaTimeModule());
        YAML_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    protected ObjectMapper objectMapper() {
        return YAML_MAPPER;
    }
}
