package com.ryoua.seckill.mapper;

import com.ryoua.seckill.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 15:56
 */
@Mapper
public interface UserMapper {

    @Select("select * from seckill_user where id = #{id}")
    public User getById(@Param("id") long id);

}
