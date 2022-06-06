package cn.codingxxm.test;

import cn.codingxxm.test.mybatis.entity.User;
import cn.codingxxm.test.mybatis.mapper.UserMapper;
import cn.codingxxm.mybatis.dict.helper.config.SysConfig;
import cn.codingxxm.mybatis.dict.helper.handler.HandlerDispatcher;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        //InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("/Users/xiaoming/Documents/git/mybatis-dict-helper/demo/src/main/resources/mybatis-config.xml");
        File file = new File("/Users/xiaoming/Documents/git/mybatis-dict-helper/demo/src/main/resources/mybatis-config.xml");
        FileInputStream inputStream = new FileInputStream(file);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

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
