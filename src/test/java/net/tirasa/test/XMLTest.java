package net.tirasa.test;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import java.util.List;
import java.util.Map;
import java.util.Set;

class XMLTest extends SerializationTest {

    private static final XmlMapper XML_MAPPER;

    static {
        XML_MAPPER = new XmlMapper();
        XML_MAPPER.registerModule(new AfterburnerModule());
        XML_MAPPER.registerModule(new JavaTimeModule());
        XML_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        XML_MAPPER.configOverride(List.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
        XML_MAPPER.configOverride(Set.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
        XML_MAPPER.configOverride(Map.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
        if (isJackson212()) {
            XML_MAPPER.enable(FromXmlParser.Feature.EMPTY_ELEMENT_AS_NULL);
        }
    }

    private static boolean isJackson212() {
        return System.getProperty("JACKSON_VERSION", "2.12").startsWith("2.12");
    }

    @Override
    protected ObjectMapper objectMapper() {
        return XML_MAPPER;
    }
}
