package tedu.jt.web.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tedu.jt.common.service.HttpClientService;
import tedu.jt.web.pojo.User;

import java.util.Map;

/**
 * User前台业务逻辑
 *
 * @author Administrator
 * @create 2017-12-20 16:12
 */
@Service
public class UserService {
    @Autowired
    private HttpClientService httpClientService;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //注册
    public String doRegister(User user) throws Exception {
        String url = "http://sso.jt.com/user/register";
        Map<String,String> params = new HashedMap();
        params.put("username",user.getUsername());
        params.put("password",user.getPassword());
        params.put("email",user.getEmail());
        params.put("phone",user.getPhone());
        params.put("user",user.getUsername());

        String jsonData = httpClientService.doPost(url,params,"utf-8");
        JsonNode jsonNode = MAPPER.readTree(jsonData);
        String username = jsonNode.get("data").asText();

        return username;
    }
    //登录
    public String doLogin(String username,String password) throws Exception {
        String url = "http://sso.jt.com/user/login";
        Map<String,String> map = new HashedMap();
        map.put("u",username);
        map.put("p",password);
        String jsonData = httpClientService.doPost(url,map,"utf-8");
        JsonNode jsonNode = MAPPER.readTree(jsonData);
        String ticket = jsonNode.get("data").asText();
        return ticket;


    }

}
