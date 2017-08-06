package cn.yinguowei.boot.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 创建 by 殷国伟 于 2017/8/6.
 */
@Controller
public class SettingsController {

    @ApiOperation(value = "系统设置")
    @GetMapping("/settings/sys")
    public String sysSettings() {
        return "settings/sysSettings";
    }
}
