package club.guanghao.springboot01cache.controller;

import club.guanghao.springboot01cache.entity.Employee;
import club.guanghao.springboot01cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 黄光昊
 * @create 2020-05-12-22:38
 */
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/emp/{id}")
    public Employee getEmpolyee(@PathVariable("id") Integer id){
        return employeeService.getEmp(id);
    }

    /**
     * 更新员工信息
     * @param employee
     * @return
     */
    @PostMapping("emp")
    public Employee modifyEmployee(Employee employee){
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @GetMapping("/delemp")
    public String removeEmployee(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }
}
