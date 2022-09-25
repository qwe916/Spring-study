package hello.typeconverter.converter;

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
}
