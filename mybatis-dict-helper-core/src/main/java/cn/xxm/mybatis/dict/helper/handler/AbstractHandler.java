package cn.xxm.mybatis.dict.helper.handler;

import cn.xxm.mybatis.dict.helper.config.SysConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractHandler implements Handler{

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

    private SqlSessionFactory sqlSessionFactory;

    private SysConfig sysConfig;

    private final List<Map<String, Object>> dictValueList = new ArrayList<>();

    public List<Map<String, Object>> getDictValueList() {
        return dictValueList;
    }

    @Override
    public void setSysConfig(SysConfig sysConfig) {
        this.sysConfig = sysConfig;
    }

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public SysConfig getSysConfig() {
        return sysConfig;
    }

    public void getDictValue(SqlSession sqlSession, HandleObject handleObject) {
        ResultSet result;
        PreparedStatement pst = null;
        try {
            pst = sqlSession.getConnection().prepareStatement(sql(handleObject));
            result = pst.executeQuery();
            ResultSetMetaData md = result.getMetaData();
            int columnCount = md.getColumnCount();
            while (result.next()) {
                Map<String,Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), result.getObject(i));
                }
                dictValueList.add(rowData);
            }

        } catch (SQLException e) {
            logger.error("getDict occur error! ", e);
        }finally {
            if(pst != null){
                try {
                    pst.close();
                } catch (SQLException e) {
                    logger.error("close PreparedStatement occur error! ", e);
                }
            }
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public void setValue(Object value, HandleObject handleObject) {
        handleObject.getMetaObject().setValue(handleObject.getProperty(), value);
    }

    private String sql(HandleObject handleObject) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select ").append(handleObject.getText())
                .append(" from ").append(handleObject.getTable())
                .append(" where ").append(handleObject.getColumn()).append(" = ").append("'").append(handleObject.getFieldValue()).append("'");
        if (!handleObject.getConditions().equals("")) {
            sqlBuilder.append(" and ").append(handleObject.getConditions());
        }
        return sqlBuilder.toString();
    }
}
