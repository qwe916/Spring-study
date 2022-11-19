package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemerControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //= GetMapping("/new-form")
    @RequestMapping(value = "/new-form", method = RequestMethod.GET)//GET 메소드에만 호출 POST와 같은 다른 메소드는 허용하지 않는다.
    public String newForm() {//ModelAndView 말고 String을 반환해도 된다.
        return "new-form";//뷰의 논리 이름을 반환할 수 있다.
    }

    //= @GetMapping
    @RequestMapping//springmvc/v2/members/members는 안되는 주소 제거
    public String members(Model model) {//Model을 매개변수로 받아와서 저장
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";

    }

    //@PostMapping("/save")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestParam("username") String username,
                             @RequestParam("age") int age,
                             Model model) {//HttpServletRequest 대시 Parameter 매개변수로 가져오기

        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);

        model.addAttribute("member", member);// = mv.addObject("member", member);//== mv.getModel().put("member", member);

        return "save-result";
    }
}
