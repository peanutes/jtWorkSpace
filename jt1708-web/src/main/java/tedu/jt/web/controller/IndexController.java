package tedu.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页controller
 *
 * @author Administrator
 * @create 2017-12-17 14:09
 */
@Controller
public class IndexController {

    //转向前台首页
    @RequestMapping("index")
    public String goIndex(){
        return "index";
    }
}
