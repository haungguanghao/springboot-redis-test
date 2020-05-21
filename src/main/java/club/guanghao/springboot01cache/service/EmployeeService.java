package club.guanghao.springboot01cache.service;

import club.guanghao.springboot01cache.entity.Employee;
import club.guanghao.springboot01cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 黄光昊
 * @create 2020-05-12-22:35
 */
@Service
@CacheConfig(cacheManager = "empRedisCacheManger")
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取员工信息
     * @Cacheable：主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
     * @param id
     * @return
     */
    @Cacheable(value = "emp")
    public Employee getEmp(Integer id){
        System.out.println("查询" + id + "号员工信息");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * 更新员工信息
     * @CachePut：保证方法被调用，又写昂结果被缓存
     * @param employee
     * @return
     */
    @CachePut(value = "emp", key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("更新"+ employee.getId() + "员工信息");
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * 删除员工信息
     * @CacheEvict:清空缓存
     *      allEntries:删除缓存所有内容
     *      beforeInvocation:如果为true,先删除缓存信息再执行方法，否则先执行方法，在删除缓存信息。区别在于方法如果有异常，则缓存数据不能删除。
     * @param id
     */
    @CacheEvict(value = "emp", key = "#id", allEntries = true, beforeInvocation = true)
    public void deleteEmp(Integer id){
        System.out.println("删除" + id + "员工信息");
//        employeeMapper.deleteEmp(id);
    }
}
