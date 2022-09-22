package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class ApiExceptionController {
    @GetMapping("api/members/{id}")
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

    @GetMapping("/api/reponse-status-ex1")
    public String reponseStautsEx1() {
        throw new BadRequestException();//400오류 발생
    }

    //ResponseStatusException 예외처리
    @GetMapping("/api/reponse-stauts-ex2")
    public String reponseStatusEx2(){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
        private String memberId;
    }
}
