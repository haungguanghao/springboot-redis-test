package club.guanghao.springboot01cache.mapper;

import club.guanghao.springboot01cache.entity.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @author 黄光昊
 * @create 2020-05-12-22:11
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @Select("select * from employee where id = #{id}")
    public Employee getEmpById(Integer id);


    /**
     * 修改员工信息
     * @param employee
     */
    @Update("update employee set lastName = #{lastName}, email = #{email}, gender = #{gender}, d_id = #{dId} where id = #{id}")
    public void updateEmp(Employee employee);


    /**
     * 添加员工信息
     * @param employee
     */
    @Insert("insert into employee(lastName, email, gender ,d_id) values(#{lastName}, #{email}, #{gender}, #{dId})")
    public void insertEmp(Employee employee);


    /**
     * 根据id删除员工信息
     * @param id
     */
    @Delete("delete from employee where id = #{id}")
    public void deleteEmp(Integer id);

}
