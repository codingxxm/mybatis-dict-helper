package cn.codingxxm.mybatis.dict.helper.interceptor;


import cn.codingxxm.mybatis.dict.helper.handler.ResultParser;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import java.sql.Statement;
import java.util.Objects;

@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class HandleDictInterceptor implements Interceptor {

    //缓存开关，先支持本地缓存，默认关闭
    //缓存刷新时间，默认3600s
    //condition用来拼接SQL条件
    //缓存的key 由表名 + 列 + 条件 + value 来确认

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object resultObj = invocation.proceed();
        if (Objects.nonNull(resultObj)) {
            ResultParser handler = new ResultParser(resultObj);
            handler.handleObj();
        }
        return resultObj;
    }

}
