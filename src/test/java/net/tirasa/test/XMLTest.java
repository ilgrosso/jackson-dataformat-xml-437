package net.tirasa.test;

import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

class XMLTest extends SerializationTest {

    private static final XmlMapper XML_MAPPER;

    static {
        if (isJackson212()) {
            XML_MAPPER = new XmlMapper(new XmlFactory() {

                private static final long serialVersionUID = 1L;

                @Override
                protected void _initFactories(final XMLInputFactory xmlIn, final XMLOutputFactory xmlOut) {
                    super._initFactories(xmlIn, xmlOut);
                    xmlOut.setProperty(WstxOutputFactory.P_AUTOMATIC_EMPTY_ELEMENTS, Boolean.FALSE);
                }
            });

            XML_MAPPER.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
            XML_MAPPER.enable(FromXmlParser.Feature.EMPTY_ELEMENT_AS_NULL);
        } else {
            XML_MAPPER = new XmlMapper();
        }

        XML_MAPPER.findAndRegisterModules();

        XML_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        XML_MAPPER.configOverride(List.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
        XML_MAPPER.configOverride(Set.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
        XML_MAPPER.configOverride(Map.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
    }

    private static boolean isJackson212() {
        return System.getProperty("JACKSON_VERSION", "2.12").startsWith("2.12");
    }

    @Override
    protected ObjectMapper objectMapper() {
        return XML_MAPPER;
    }
}
