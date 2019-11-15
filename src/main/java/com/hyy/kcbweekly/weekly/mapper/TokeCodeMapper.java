package com.hyy.kcbweekly.weekly.mapper;

import com.hyy.kcbweekly.weekly.entity.TokenCode;
import com.hyy.kcbweekly.weekly.mapper.provider.TokenCodeProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TokeCodeMapper {

    @SelectProvider(type = TokenCodeProvider.class, method = "findToken")
    //查询token
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "token", column = "token"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "create_time", column = "create_time"),
    })
    TokenCode findToken(String token);

    //  对象参数传递
    //    @Insert("INSERT INTO USER(NAME) VALUES(#{name})")
    //    int insert3(User user);
    @Insert("insert into kcb_token(id,mobile,token,create_time) values(#{id},#{mobile},#{token},#{create_time}) ")
    int insert(TokenCode tokenCode);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "token", column = "token"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "create_time", column = "create_time"),
    })
    @Select("select * from kcb_token")
    List<TokenCode> findAllToken();

    @Delete("delete from kcb_token where id=#{id}")
    int delete(TokenCode tokenCode);
    @Update("update kcb_token set mobile = #{mobile},token = #{token},create_time = #{create_time} where id = #{id}")
    int update(TokenCode tokenCode);
}
