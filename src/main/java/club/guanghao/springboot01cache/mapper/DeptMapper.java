package club.guanghao.springboot01cache.mapper;

import club.guanghao.springboot01cache.entity.Department;
import org.apache.ibatis.annotations.Select;

/**
 * @author 黄光昊
 * @create 2020-05-15-20:29
 */
public interface DeptMapper {

    @Select("select * from department where id = #{id}")
    public Department getDeptById(Integer id);
}
