package ApiThreadServices.ApiThreadServices.v1.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.List;

@Component
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    public List<String> valueGet(String key) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.mget(key);
        }
    }
    public String get(String key) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    public String setValue(String key, String value) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.set(key,  value);
        }
    }

    public String set(String key, String value, SetParams params) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.set(key, value, params);
        }
    }

    public boolean exists(String key) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    public Long del(String key) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.del(key);
        }
    }

    public boolean setNx(String key, String value, int ttl) {
        try (var jedis = jedisPool.getResource()) {
            return jedis.set(key, value, new SetParams().nx().ex(ttl)) != null;
        }
    }
}




