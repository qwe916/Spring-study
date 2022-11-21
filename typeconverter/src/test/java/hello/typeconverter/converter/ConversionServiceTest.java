package hello.typeconverter.converter;

import hello.typeconverter.type.ipPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {
    @Test
    void conversionService() {
        /**
         * ConversionService를 이용하여 Converter들을 등록하여 쓰기 쉽게 만든다.
         */
        //등록
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new StringToIntegerConverter());
        service.addConverter(new IntegerToStringConverter());
        service.addConverter(new StringToIpPortConverter());
        service.addConverter(new IpPortToStringConverter());

        //사용
        Integer result = service.convert("10", Integer.class);
        Assertions.assertThat(service.convert("10", Integer.class)).isEqualTo(10);
        Assertions.assertThat(service.convert(10, String.class)).isEqualTo("10");
        ipPort ip = service.convert("127.0.0.1:8080", ipPort.class);
        Assertions.assertThat(ip).isEqualTo(new ipPort("127.0.0.1", 8080));

        String convert = service.convert(new ipPort("127.0.0.1", 8080), String.class);
        Assertions.assertThat(convert).isEqualTo("127.0.0.1:8080");

    }
}
