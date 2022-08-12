package hello.thymleaf.basic;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    /**
     * text vs utext
     * text는 <>를 escape로 처리 utext는 escape로 처리하지 않는다(utext가 html 속성 사용)
     * utext만을 사용한다면 <></>를 표현하고 싶을 때 표현할 수 없기 때문에 (기본은 utext를 사용하지 않는것이 좋다)
     */
    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-unescaped";
    }
}
