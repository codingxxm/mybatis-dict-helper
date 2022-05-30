package cn.codingxxm.mybatis.dict.helper.handler;

import cn.codingxxm.mybatis.dict.helper.config.SysConfig;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.SqlSessionFactory;

public class HandlerDispatcher {

    private static SqlSessionFactory sqlSessionFactory;

    private static SysConfig sysConfig;

    private HandleObject handleObject;

    public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        HandlerDispatcher.sqlSessionFactory = sqlSessionFactory;
    }

    public static void setSysConfig(SysConfig sysConfig) {
        HandlerDispatcher.sysConfig = sysConfig;
    }

    public HandlerDispatcher(String property, String text, String column, String table, String conditions, MetaObject metaObject, Object fieldValue) {
        handleObject = new HandleObject();
        handleObject.setMetaObject(metaObject);
        handleObject.setColumn(column);
        handleObject.setConditions(conditions);
        handleObject.setProperty(property);
        handleObject.setTable(table);
        handleObject.setFieldValue(fieldValue);
        handleObject.setText(text);
    }

    public void dispatch() {
        Handler handler = sysConfig.getHandler();
        handler.setSqlSessionFactory(sqlSessionFactory);
        handler.setSysConfig(sysConfig);
        handler.handle(handleObject);
    }
}
