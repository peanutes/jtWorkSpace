package tedu.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页跳转
 *
 * @author Administrator
 * @create 2017-12-06 9:58
 */
@Controller
public class IndexController {
    @RequestMapping("/page/{pageName}")
    public String goHome(@PathVariable String pageName){
        return pageName;
    }
}
