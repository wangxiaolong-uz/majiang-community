package life.majiang.community.community.mapper;

import life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}

