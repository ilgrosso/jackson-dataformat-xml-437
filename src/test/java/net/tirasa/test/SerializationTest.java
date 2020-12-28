package net.tirasa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

abstract class SerializationTest {

    protected abstract ObjectMapper objectMapper();

    @Test
    void emptyListAsRoot() throws IOException {
        List<POJO> original = new ArrayList<>();

        StringWriter writer = new StringWriter();
        objectMapper().writeValue(writer, original);

        List<POJO> actual = objectMapper().readValue(writer.toString(), new TypeReference<List<POJO>>() {
        });
        assertEquals(original, actual);
    }

    @Test
    void emptyListAsMember() throws IOException {
        Root original = new Root();

        StringWriter writer = new StringWriter();
        objectMapper().writeValue(writer, original);

        Root actual = objectMapper().readValue(writer.toString(), Root.class);
        assertEquals(original, actual);
    }
}
