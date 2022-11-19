package hello.springmvc.basic.reponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/reponse-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("reponse/hello")
                .addObject("data", "hello!");
        return mav;
    }
    //뷰의 논리 이름 반환
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }
    //경로의 이름과 반환할 뷰의 이름이 같으면 반환값 생략가능
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
