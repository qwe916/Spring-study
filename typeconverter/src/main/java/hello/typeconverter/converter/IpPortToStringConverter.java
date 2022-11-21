package hello.typeconverter.converter;

import hello.typeconverter.type.ipPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<ipPort,String> {
    @Override
    public String convert(ipPort source) {
        log.info("convert source = {}", source);
        //IpPort -> "127.0.0.1:8080"
        return source.getIp() + ":" + source.getPort();
    }
}
