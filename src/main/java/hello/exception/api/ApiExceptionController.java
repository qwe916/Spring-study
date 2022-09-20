package hello.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionController {
    @GetMapping("api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        //정상 작동시 문제가 없으나 오류 발생시 오류페이지가 보인다. 그러나 클라이언트는 json을 받아야 한다.
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }

        return new MemberDto(id, "hello" + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
        private String memberId;
    }
}
