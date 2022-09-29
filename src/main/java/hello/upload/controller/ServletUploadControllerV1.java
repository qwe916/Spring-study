package hello.upload.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
@Slf4j
@Controller
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {
    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }
    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {
        log.info("request={}", request);
        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        //logging.level.org.appache.coyote.http11=debug 는 서버에서 http 메시지를 로그로 남겨 볼 수 있다.
        //Http 메시지 part
        Collection<Part> parts = request.getParts();
        log.info("parts={}", parts);
        return "upload-form";
    }
}



