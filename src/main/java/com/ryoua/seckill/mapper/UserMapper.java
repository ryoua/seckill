package com.ryoua.seckill.mapper;


import com.ryoua.seckill.entity.User;
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
    @Select(value = "select * from sk_user where id = #{id}")
    public User getUserById(@Param("id") long id);
}
