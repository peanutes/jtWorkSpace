package tedu.jt.sso.mapper;

import tedu.jt.common.mapper.SysMapper;
import tedu.jt.sso.pojo.User;

import java.util.Map;

/**
 * usermapper接口
 *
 * @author Administrator
 * @create 2017-12-20 15:24
 */
public interface UserMapper extends SysMapper<User>{

    public Integer check(Map<String,String> map);
}
