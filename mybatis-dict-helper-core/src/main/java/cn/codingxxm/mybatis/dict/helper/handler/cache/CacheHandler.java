package cn.codingxxm.mybatis.dict.helper.handler.cache;

import cn.codingxxm.mybatis.dict.helper.handler.AbstractHandler;
import cn.codingxxm.mybatis.dict.helper.handler.HandleObject;
import cn.codingxxm.mybatis.dict.helper.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CacheHandler extends AbstractHandler implements Handler {

    private static final Logger logger = LoggerFactory.getLogger(CacheHandler.class);

    @Override
    public void handle(HandleObject handleObject) {

        String cacheKey = cacheKey(handleObject);
        Object cacheValue = CacheContext.getCacheValue(cacheKey);
        if (Objects.nonNull(cacheValue)) {
            logger.debug("hit cache [{}]", cacheKey);
            setValue(cacheValue, handleObject);
            return;
        }

        getDictValue(getSqlSessionFactory().openSession(), handleObject);
        List<Map<String, Object>> list = getDictValueList();
        if (!list.isEmpty()) {
            if (list.size() > 1) {
                logger.warn("dict data query return multi value, data size is  [{}], use first value",list.size());
            }
            CacheContext.setCache(cacheKey, list.get(0).get(handleObject.getText()), getSysConfig().getExpire());
            setValue(list.get(0).get(handleObject.getText()), handleObject);
        } else {
            logger.warn("dict data query empty value, [{}]", handleObject);
        }

    }

    private String cacheKey(HandleObject handleObject) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(handleObject.getTable()).append("_")
                .append(handleObject.getText()).append("_")
                .append(handleObject.getColumn()).append("_")
                .append(handleObject.getFieldValue()).append("_")
                .append(handleObject.getConditions());
        return keyBuilder.toString();
    }
}
