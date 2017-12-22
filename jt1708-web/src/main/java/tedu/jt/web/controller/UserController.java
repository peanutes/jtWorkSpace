package tedu.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tedu.jt.common.util.CookieUtils;
import tedu.jt.common.vo.SysResult;
import tedu.jt.web.pojo.User;
import tedu.jt.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录注册控制跳转
 *
 * @author Administrator
 * @create 2017-12-20 16:10
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    //转向注册页面/user/register.html
    @RequestMapping("/user/register")
    public String register(){
        return "register";
    }
    //转向登录页面
    @RequestMapping("/user/login")
    public String login(){
        return "login";
    }
    //注册
    @RequestMapping("/user/doRegister")
    @ResponseBody
    public SysResult doRegister(User user) throws Exception {
        String username=userService.doRegister(user);
        return SysResult.oK(username);

    }
    //登录
    @RequestMapping("/user/doLogin")
    @ResponseBody
    public SysResult doLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ticket = userService.doLogin(username,password);
        CookieUtils.setCookie(request,response,"JT_TICKET",ticket);
        return SysResult.oK();
    }
    //退出登录
    @RequestMapping("user/logout.html")
    public String loginOut(HttpServletResponse response,HttpServletRequest request){
        CookieUtils.deleteCookie(request,response,"JT_TICKET");
        return "index";
    }

}
