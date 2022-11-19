package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestCotroller {
    //private final Logger log = LoggerFactory.getLogger(getClass()); @Slf4j 가 만들어준다.
    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        log.info("info log=" + name);//은 사용해서는 안된다. 레벨 설정시 +변수로 해놓는다면 변수에 해당 데이터를 먼저 넣은후 연산을 하여 CPU, 매모리 등을 사용하게 되어 쓸 데 없는 리소스를 사용하게 된다.
        //System.out.println();는 무조건 남기 때문에 시스템적으로 좋지 못하다 로그를 남겨 보고싶은 로그만 볼 수 있게한다.
        log.trace("trace log={}",name);//파라미터로 넘기면 레벨에 따라 로직이 중지된다.
        log.debug("debug log={}",name);
        log.info("info log= {}",name);//많은 정보를 알 수 있다.
        log.warn("warn log={}",name);
        log.error("error log={}",name);

        return "ok";//Controller는 뷰 이름을 반환하지만 RestCotroller는 데이터를 반환한다.
    }
}
