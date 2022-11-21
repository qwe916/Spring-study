package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad")//http status를 지정해주는 에노테이션 messages Source를 사용하여 reason 설정도 가능
public class BadRequestException extends RuntimeException {
}
