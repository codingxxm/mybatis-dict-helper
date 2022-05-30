package cn.codingxxm.mybatis.dict.helper.handler.cache;

import cn.codingxxm.mybatis.dict.helper.handler.ExpireObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

public class CacheContext {

    private static final ConcurrentHashMap<String, Object> cacheMap = new ConcurrentHashMap<>();

    private static final DelayQueue<ExpireObject> delayQueue = new DelayQueue<>();

    public static ExpireObject takeObject() throws InterruptedException {
        return delayQueue.take();
    }

    public static void setCache(String cacheKey, Object cacheValue, long expire) {
        cacheMap.put(cacheKey, cacheValue);
        delayQueue.add(new ExpireObject(cacheKey, expire));
    }

    public static Object getCacheValue(String cacheKey) {
        return cacheMap.get(cacheKey);
    }

    public static void cleanExpireObject(String cacheKey) {
        cacheMap.remove(cacheKey);
    }

}
