package club.guanghao.springboot01cache.controller;

import club.guanghao.springboot01cache.entity.Department;
import club.guanghao.springboot01cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄光昊
 * @create 2020-05-15-20:31
 */
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDeptById(@PathVariable("id") Integer id){
        Department deptById = deptService.getDeptById(id);
        return deptById;
    }
}
