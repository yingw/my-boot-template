package cn.yinguowei.boot.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yingu on 2017/7/19.
 */
public class MyErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String handleError() {
        return "404";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
