package cn.codingxxm.mybatis.dict.helper.handler.db;

import cn.codingxxm.mybatis.dict.helper.handler.AbstractHandler;
import cn.codingxxm.mybatis.dict.helper.handler.HandleObject;
import cn.codingxxm.mybatis.dict.helper.handler.Handler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;


public class DbHandler extends AbstractHandler implements Handler {

    @Override
    public void handle(HandleObject handleObject) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        getDictValue(sqlSession, handleObject);
        List<Map<String, Object>> list = getDictValueList();
        if (!list.isEmpty()) {
            setValue(list.get(0).get(handleObject.getText()), handleObject);
        }

    }

}
