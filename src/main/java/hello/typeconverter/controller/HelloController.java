package hello.typeconverter.controller;

import hello.typeconverter.type.ipPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    /**
     * http 요청 파라미터는 모두 문자로 처리된다. 따라서 다른 타입으로 요청하고 싶으면 typeconverter를 이용하여 타입을 변환한다.
     */

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
    //Converter를 등록한다면 spring에서 converter를 호출하여 컨버터 후 값을 넘겨준다.(그러나 스프링 내부에서 많은 기본 컨버터를 제공하기 떄문에 등록하지 않아도 되는 converter도 존재할 수 있다.)
    //그러나 기본 컨버터 외의 컨버터를 추가하면 추가한 컨버터가 기본 컨버터보다 우선순위를 갖는다.
    @GetMapping("/hello-v2")
    public String helloV1(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam ipPort ipPort) {
        System.out.println("ipPort.Ip = " + ipPort.getIp());
        System.out.println("ipPort.Port = " + ipPort.getPort());
        return "ok";
    }
}
