package cn.xxm.test;

import cn.xxm.test.mybatis.entity.User;
import cn.xxm.test.mybatis.mapper.UserMapper;
import cn.xxm.mybatis.dict.helper.config.SysConfig;
import cn.xxm.mybatis.dict.helper.handler.HandlerDispatcher;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        HandlerDispatcher.setSqlSessionFactory(sqlSessionFactory);
        SysConfig sysConfig = new SysConfig();
        sysConfig.setCache(true);
        sysConfig.setExpire(2);
        HandlerDispatcher.setSysConfig(sysConfig);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.listAll();
        System.out.println(users);
        sqlSession.close();

        TimeUnit.SECONDS.sleep(3);
    }

}
