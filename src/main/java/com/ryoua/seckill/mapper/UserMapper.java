package com.ryoua.seckill.mapper;


import com.ryoua.seckill.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author ryoua Created on 2019-07-06
 */
@Mapper
public interface UserMapper {

    /**
     * 通过Id得到用户
     * @param id
     * @return
     */
    @Select(value = "select * from user where id = #{id}")
    public User getUserById(@Param("id") int id);

    /**
     * 增加用户
     * @param user
     * @return
     */
    @Insert(value = "insert into user(id, name) values (#{id}, #{name}")
    public int AddUser(User user);
}
