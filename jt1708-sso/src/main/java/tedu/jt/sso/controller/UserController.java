package tedu.jt.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tedu.jt.common.service.RedisService;
import tedu.jt.common.vo.SysResult;
import tedu.jt.sso.pojo.User;
import tedu.jt.sso.service.UserService;

import javax.rmi.CORBA.Tie;

/**
 * user控制跳转
 *
 * @author Administrator
 * @create 2017-12-20 15:50
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;


    /**
     * 通过ajax检查用户电话邮箱是否已存在
     * @param param
     * @param type
     * @return
     */
    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public SysResult check(@PathVariable String param,@PathVariable Integer type){
        Boolean b = userService.check(param,type);
        return SysResult.oK(b);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public SysResult register(User user){
        String username = userService.saveRegister(user);
        return SysResult.oK(username);
    }

    /**
     * 从前台传来姓名和密码
     * @param u
     * @param p
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public SysResult login(String u,String p){
        String ticket = userService.getLogin(u,p);
        return SysResult.oK(ticket);
    }
    @RequestMapping("/query/{ticket}")
    @ResponseBody
    public SysResult queryByTicket(@PathVariable String ticket){
        String userData = redisService.get(ticket);
        return SysResult.oK(userData);
    }

}
