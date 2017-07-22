package cn.yinguowei.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by yingu on 2017/7/22.
 */
@Controller
public class LoginController {

    @GetMapping("/login2")
    public String login() {
        return "login2";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
