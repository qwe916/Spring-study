package hello.typeconverter.formatter;

import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.type.ipPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

public class FormattingCoversionServiceTest {
    @Test
    void formattingConversionService() {
        //DefaultFormattingConversionService는 ConversionService도 상속받고 있어 컨버터 포맷터 둘다 등록 가능하다.
        //DefaultFormattingConversionService를 상속받은 WebConversionService를 내부에서 사용하여 여기에다가 포멧터를 등록한다.
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        //컨버터 등록
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        //포맷터 등록
        conversionService.addFormatter(new MyNumberFormatter());
        //컨버터 사용
        ipPort ipPort = conversionService.convert("127.0.0.1:8080", hello.typeconverter.type.ipPort.class);
        Assertions.assertThat(ipPort).isEqualTo(new ipPort("127.0.0.1", 8080));
        //포맷터 사용
        String convert = conversionService.convert(1000, String.class);
        Assertions.assertThat(convert).isEqualTo("1,000");
        Long convert1 = conversionService.convert("1,000", Long.class);
        Assertions.assertThat(convert1).isEqualTo(1000);

    }
}
