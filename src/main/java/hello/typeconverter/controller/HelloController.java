package hello.typeconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    /**
     * http 요청 파라미터는 모두 문자로 처리된다. 따라서 다른 타입으로 요청하고 싶으면 typeconverter를 이용하여 타입을 변환한다.
     * */

    //HttpServletRequest 파라미터 조회
    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data");//문자 타입 조회
        Integer intValue = Integer.parseInt(data);
        System.out.println("intValue = " + intValue);
        return "ok";
    }

    //@RequestParam 파라미터를 조회
    //@RequestParam은 자동으로 스프링이 내부에서 자동으로 타입 변환을 해준다. (@ModelAttribute, @PathVariable에서도 확인 가능)
    @GetMapping("/hello-v2")
    public String helloV1(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }
}
