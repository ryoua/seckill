package com.ryoua.seckill.mapper;

import com.ryoua.seckill.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 15:56
 */
@Mapper
public interface UserMapper {

    @Select("select * from seckill_user where id = #{id}")
    public User getById(@Param("id") long id);

    @Update("update seckill_user set password = #{password} where id = #{id}")
    public void update(User toBeUpdate);
}
