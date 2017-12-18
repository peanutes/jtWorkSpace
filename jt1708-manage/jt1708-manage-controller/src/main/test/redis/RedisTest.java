package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

/**
 * redis测试
 *
 * @author Administrator
 * @create 2017-12-17 9:59
 */
public class RedisTest {


    public void JedisTest(){
        Jedis jedis = new Jedis("192.168.163.10",6382);
        for (int i=0;i<50;i++){
            jedis.set(i+"","haha"+i);
        }
        jedis.close();
    }

    public void JedissTest(){
        List<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("192.168.163.10",6382);
        JedisShardInfo jedisShardInfo2 = new JedisShardInfo("192.168.163.10",6383);
        JedisShardInfo jedisShardInfo3 = new JedisShardInfo("192.168.163.10",6384);
        infoList.add(jedisShardInfo1);
        infoList.add(jedisShardInfo2);
        infoList.add(jedisShardInfo3);
        ShardedJedis shardedJedis = new ShardedJedis(infoList);
        for (int i=0;i<60;i++){
            shardedJedis.set(i+"","haha"+i);
        }
        shardedJedis.close();
    }
}
