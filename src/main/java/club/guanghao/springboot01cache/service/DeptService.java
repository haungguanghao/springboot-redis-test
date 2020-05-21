package club.guanghao.springboot01cache.service;

import club.guanghao.springboot01cache.entity.Department;
import club.guanghao.springboot01cache.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author 黄光昊
 * @create 2020-05-15-20:28
 */
@Service
@CacheConfig(cacheManager = "deptRedisCacheManger")
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Qualifier("deptRedisCacheManger")
    @Autowired
    private RedisCacheManager deptRedisCacheManger;


//    @Cacheable(value = "dept")
//    public Department getDeptById(Integer id){
//        System.out.println("查询" + id + "号部门");
//        Department department = deptMapper.getDeptById(id);
//        return department;
//    }

    public Department getDeptById(Integer id){
        System.out.println("查询" + id + "号部门");
        Department department = deptMapper.getDeptById(id);
        Cache cache = deptRedisCacheManger.getCache("dept");
        cache.put(id, department);
        return department;
    }
}
