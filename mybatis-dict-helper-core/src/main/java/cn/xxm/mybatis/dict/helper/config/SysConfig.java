package cn.xxm.mybatis.dict.helper.config;

import cn.xxm.mybatis.dict.helper.handler.ExpireObject;
import cn.xxm.mybatis.dict.helper.handler.cache.CacheContext;
import cn.xxm.mybatis.dict.helper.handler.Handler;
import cn.xxm.mybatis.dict.helper.handler.cache.CacheHandler;
import cn.xxm.mybatis.dict.helper.handler.db.DbHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysConfig {

    private static final Logger logger = LoggerFactory.getLogger(SysConfig.class);

    private static final Runnable taskRunnable = () -> {
        while (true) {
            try {
                ExpireObject expireObject = CacheContext.takeObject();
                CacheContext.cleanExpireObject(expireObject.getKey());
                logger.debug("[{}] been cleaned", expireObject.getKey());
            } catch (InterruptedException e) {
                logger.error("take expire object occur error !", e);
            }
        }
    };

    public SysConfig() {
        Thread cleanerThread = new Thread(taskRunnable, "dict_cleaner");
        cleanerThread.setDaemon(true);
        cleanerThread.start();
    }

    private boolean cache;

    private long expire;

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public Handler getHandler() {
        return cache ? new CacheHandler() : new DbHandler();
    }

}
