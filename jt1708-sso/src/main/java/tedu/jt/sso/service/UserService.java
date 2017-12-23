package tedu.jt.sso.service;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.mapper.Mapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tedu.jt.common.service.RedisService;
import tedu.jt.sso.mapper.UserMapper;
import tedu.jt.sso.pojo.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * user逻辑业务
 *
 * @author Administrator
 * @create 2017-12-20 15:34
 */
@Service
public class UserService extends BaseService<User>{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 检查数据是否存在
     * @param param
     * @param type
     * @return
     */
    public boolean check(String param,Integer type){
        Map<String,String> map = new HashMap();
        if(1==type){
            map.put("colname","username");
        }else if(2==type){
            map.put("colname","phone");
        }else {
            map.put("colname","email");
        }
        map.put("val",param);
        int count = userMapper.check(map);
        return count==0 ? false : true;
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    public String saveRegister(User user){

        if(StringUtils.isEmpty(user.getEmail())){
            user.setEmail(user.getPhone());
        }
        if(StringUtils.isEmpty(user.getPhone())){
            user.setPhone(user.getEmail());
        }
        String password = DigestUtils.md5Hex(user.getPassword());

        user.setPassword(password);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        userMapper.insertSelective(user);
        return user.getUsername();
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public String getLogin(String username,String password){
        String ticket = null;
        User user = new User();
        user.setUsername(username);
        User curUser = super.queryByWhere(user);
        if(curUser!=null){
            String curPassword = DigestUtils.md5Hex(password);
            if(curPassword.equals(curUser.getPassword())){
                ticket = DigestUtils.md5Hex("TICKET_"+curUser.getUsername()+System.currentTimeMillis());

                try {
                    redisService.set(ticket, MAPPER.writeValueAsString(curUser),60*60*24);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }


            }
        }
        return ticket;
    }
}
