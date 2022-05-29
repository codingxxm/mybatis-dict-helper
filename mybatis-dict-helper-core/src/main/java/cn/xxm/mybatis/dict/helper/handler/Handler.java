package cn.xxm.mybatis.dict.helper.handler;

import cn.xxm.mybatis.dict.helper.config.SysConfig;
import org.apache.ibatis.session.SqlSessionFactory;

public interface Handler {

    void setSysConfig(SysConfig sysConfig);

    void handle(HandleObject handleObject);

    void  setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);
}
