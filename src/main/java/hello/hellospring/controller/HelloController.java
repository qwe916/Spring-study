package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";//resorce의 hello와 동일
    }

    @GetMapping("hello-mvc")//MVC 방식
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {//required는 default가 true
        model.addAttribute("name", name);
        return "hello-templete";//view 반환
    }

    @GetMapping("hello-string")//API 방식
    @ResponseBody//http의 body 부분에 내용을 넣어서 보낼것이다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;//templete을 반환하는 것이 아닌 그냥 데이터만을 반환한다.
    }

    @GetMapping("hello-api")
    @ResponseBody//이 에노테이션이 붙어있으면 http 응답에 그냥 데이터를 넣어 보냄( 객체라면 json 방식으로 데이터를 만들어 보낸다.) -HttpMessageConverter가 정함 - MappingJackson2HttpMessageConverter가 json으로 변환
    // 원하는 방식으로 반환 가능(xml 등등) 해당 타입으로 반환하는 라이브러리만 넣어놓는다면..
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//객체 반환(json) xml 방식은 무겁고 복잡 그러나 json은 가볍고 직관적
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
