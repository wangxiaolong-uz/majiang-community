package life.majiang.community.community.mapper;

import life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author wxl
 * @date 2020/3/6 - 17:12
 */

@Component
@Mapper
public interface UserMapper {

    @Insert("insert into users(uname, account_id, token, gmt_create, gmt_modify) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModify})")
    void insert(User user);

    @Select("select * from users where token = #{token}")
    @Results({
            @Result(column = "account_id", property = "accountId"),
            @Result(column = "uname", property = "name"),
            @Result(column = "gmt_create", property = "gmtCreate"),
            @Result(column = "gmt_modify", property = "gmtModify")
            })
    User findByToken(@Param("token") String token);
}

