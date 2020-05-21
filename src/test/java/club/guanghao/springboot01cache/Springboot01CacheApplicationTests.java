package club.guanghao.springboot01cache;

import club.guanghao.springboot01cache.entity.Employee;
import club.guanghao.springboot01cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Springboot01CacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test(){
//        stringRedisTemplate.opsForValue().append("msg", "hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
//        stringRedisTemplate.opsForList(); List集合
//        stringRedisTemplate.opsForHash(); Hash集合
//        stringRedisTemplate.opsForSet();  set集合
//        stringRedisTemplate.opsForZSet(); Zset有序集合

        System.out.println(msg);
    }

    @Test
    void contextLoads() {
        Employee empById = employeeMapper.getEmpById(2);
        redisTemplate.opsForValue().set("emp-02", empById);
    }

    @Test
    public void testSelectEmpById(){
        Employee empById = employeeMapper.getEmpById(1);
        System.out.println(empById);
    }

}
