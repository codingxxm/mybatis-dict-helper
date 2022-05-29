package cn.xxm.mybatis.dict.helper.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "mybatis-dict-helper"
)
public class MybatisDictHelperProperties {

    private boolean enableCache = true;

    private long expire = 3600;

    public void setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public boolean isEnableCache() {
        return enableCache;
    }

    public long getExpire() {
        return expire;
    }
}
