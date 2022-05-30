package cn.codingxxm.mybatis.dict.helper.handler;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ExpireObject implements Delayed {

    private final String key;

    private final Long time;

    public ExpireObject(String key, long time) {
        this.key = key;
        this.time = System.currentTimeMillis() + time*1000;
    }

    public String getKey() {
        return key;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        ExpireObject expireObject = (ExpireObject) o;
        long diff = this.time - expireObject.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

}
