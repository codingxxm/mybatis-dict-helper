package cn.xxm.test.mybatis.mapper;

import cn.xxm.test.mybatis.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoming
 * @since 2022-05-29
 */
public interface UserMapper {

    List<User> listAll();

}
