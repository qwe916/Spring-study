package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class ApiExceptionV2Controller {

    //@ExceptionHandler는 이 컨트롤러 내에서만 작동한다.
    //예외 발생시 ExceptionHandlerExceptionResolver가 실행되고 컨트롤러에 @ExeptionHandler를 찾은 후 실행. 정상흐름으로 처리되어 json으로 반환
    //만약 정상적인 처리(200)으로 처리하기 싫다면 @ResponseStatus를 붙여 json으로 반환하되 200이 아닌 status http code를 넘길 수 있다.
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
    @GetMapping("api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {

        //정상 작동시 문제가 없으나 오류 발생시 오류페이지가 보인다. 그러나 클라이언트는 json을 받아야 한다.
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello" + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
        private String memberId;
    }
}
