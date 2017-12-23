package tedu.jt.web.controller.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tedu.jt.common.service.HttpClientService;
import tedu.jt.common.util.CookieUtils;
import tedu.jt.web.pojo.User;
import tedu.jt.web.threadlocal.UserThreadlocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车拦截器
 *
 * @author Administrator
 * @create 2017-12-23 9:55
 */
public class CartIntercetor implements HandlerInterceptor{
    @Autowired
    private HttpClientService httpClientService;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取UserId
        String ticket = CookieUtils.getCookieValue(request,"JT_TICKET");
        if(StringUtils.isNotEmpty(ticket)){
            //从redis中获取ticket
            String url = "http://sso.jt.com/user/query/"+ticket;
            String jsonData = httpClientService.doGet(url);
            if(StringUtils.isNotEmpty(jsonData)){
                try {
                    JsonNode jsonNode = MAPPER.readTree(jsonData);
                    String userData = jsonNode.get("data").asText();
                    User user = MAPPER.readValue(userData,User.class);
                    UserThreadlocal.set(user);
                    //false 不放行 true 放行
                    return true;
                }catch (Exception e){

                }
            }
        }
        response.sendRedirect("/user/login.html");
        UserThreadlocal.set(null);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
