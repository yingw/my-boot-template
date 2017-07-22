package cn.yinguowei.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by yingu on 2017/7/18.
 */
@Controller
public class HelloController {


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/session")
    @ResponseBody
    public String session(HttpSession session) {
        UUID uuid = (UUID) session.getAttribute("uid");
        if (uuid == null) uuid = UUID.randomUUID();
        session.setAttribute("uid", uuid);
        return uuid.toString();
    }

/*
    @GetMapping("/{somepath}")
    public String test(@PathVariable("somepath") String somepath) {
        System.out.println("somepath = [" + somepath + "]");
        return somepath;
    }
*/

}
