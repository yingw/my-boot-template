package cn.yinguowei.boot.configration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Created by yingu on 2017/7/27.
 */
@Component
public class MyAuditorAware implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return "system";
    }
}
