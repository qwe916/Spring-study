package hello.typeconverter.converter;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import hello.typeconverter.type.ipPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class ConverterTest {
    @Test
    void stringtoInteger() {
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        Integer result = stringToIntegerConverter.convert("10");
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter integerToStringConverter = new IntegerToStringConverter();
        String result = integerToStringConverter.convert(10);
        Assertions.assertThat(result).isEqualTo("10");
    }

    @Test
    void StringToIpPort() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        ipPort source = new ipPort("127.0.0.1", 8080);
        String result = converter.convert(source);
        Assertions.assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void IpPortToString() {
        StringToIpPortConverter con = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        ipPort result = con.convert(source);
        Assertions.assertThat(result).isEqualTo(new ipPort("127.0.0.1", 8080));
    }
}
