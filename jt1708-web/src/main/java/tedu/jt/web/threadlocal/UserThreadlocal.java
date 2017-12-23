package tedu.jt.web.threadlocal;

import tedu.jt.web.pojo.User;

/**
 * 线程锁
 *
 * @author Administrator
 * @create 2017-12-23 10:17
 */
public class UserThreadlocal {
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<User>();

    public static void set(User user){
        USER_THREAD_LOCAL.set(user);
    }
    public static User get() {
        return USER_THREAD_LOCAL.get();
    }
    public static Long getUserId(){
        try {
           return USER_THREAD_LOCAL.get().getId();
        }catch (Exception e){
            return null;
        }
    }
}
