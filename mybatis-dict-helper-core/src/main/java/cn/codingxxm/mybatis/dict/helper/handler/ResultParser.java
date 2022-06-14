package cn.codingxxm.mybatis.dict.helper.handler;

import cn.codingxxm.mybatis.dict.helper.anno.Dict;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class ResultParser {

    private final Object resultObject;

    public ResultParser(Object resultObject) {
        this.resultObject = resultObject;
    }

    public void handleObj() throws IllegalAccessException {
        if (resultObject instanceof ArrayList) {
            ArrayList<?> obj = (ArrayList<?>) resultObject;
            for (Object o : obj) {
                doHandle(o);
            }
        } else {
            doHandle(resultObject);
        }
    }

    private void doHandle(Object resultObject) throws IllegalAccessException {
        Field[] fields = resultObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            Dict annotation = field.getDeclaredAnnotation(Dict.class);
            if (annotation != null) {
                field.setAccessible(true);
                Object fieldValue = field.get(resultObject);
                if (Objects.isNull(fieldValue)) {
                    continue;
                }
                String property = annotation.property();
                String column = annotation.column();
                String table = annotation.table();
                String conditions = annotation.condition();
                String text = annotation.text();
                MetaObject metaObject = SystemMetaObject.forObject(resultObject);
                HandlerDispatcher dispatcher = new HandlerDispatcher(property, text, column, table, conditions, metaObject, fieldValue);
                dispatcher.dispatch();
            }
        }
    }
}
