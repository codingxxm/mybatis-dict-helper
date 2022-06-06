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
