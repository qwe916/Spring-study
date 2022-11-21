package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Advice는 대상으로 지정한 컨트롤러에 @ExcpetionHandler 기능을 부여해주는 역할을 한다.
 * 대상을 지정해주지 않으면 모든 컨트롤러에 적용된다.
 * 대상을 지정할 떄는 @ControllerAdvice 파라미터로 패키지넘겨주거나 , 어노테이션을 지정해주거나, 특정 클래스를 만들어 넣어준다.
 */
@Slf4j
@RestControllerAdvice("hello.exception.api.ApiExceptionV2Controller")//특정 클래스
//@RestControllerAdvice(annotations = RestController.class) 어노테이션
//@RestControllerAdvice("hello.exception.api") 패키지
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    //Exception은 상세오류를 제외한 예상하지 못한 오류나 나머지 오류를 이 컨트롤러가 처리한다. 다른 UserException이나 IllegalArgumentExceptio같은 오류는 자기 자식들의 오류를 포함하여 처리하고 Exception은 지정하지 못한 오류들을 처리한다.
    //따라서 자세한 오류가 우선권을 갖고 넓은 범위는 후순위를 갖는다.
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부오류");
    }
}
