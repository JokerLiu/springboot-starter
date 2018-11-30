package com.joker.cache.mapper;

import com.joker.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @Author: Joker
 * @Description:
 * @Date: Created in 2018/11/30 16:00
 */
@Mapper
public interface EmployeeMapper {

    // @Select("select * from employee where id = #{id}")
    Employee getEmpById(Integer id);

    // @Update("update employee set lastName = #{lastName} where id = #{id}")
    Integer updateEmp(Employee emp);

    // @Delete("delete from employee where id = #{id}")
    Integer deleteEmp(Integer id);

    // @Insert("insert into employee(lastName,email) values(#{lastName},#{email}) ")
    Integer insertEmp(Employee emp);

}