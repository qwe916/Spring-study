package hello.typeconverter.type;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ipPort {

    public ipPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private String ip;
    private int port;
}
