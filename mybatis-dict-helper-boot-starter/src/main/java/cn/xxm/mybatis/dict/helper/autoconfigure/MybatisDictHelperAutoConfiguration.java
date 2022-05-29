package cn.xxm.mybatis.dict.helper.autoconfigure;

import cn.xxm.mybatis.dict.helper.config.SysConfig;
import cn.xxm.mybatis.dict.helper.handler.HandlerDispatcher;
import cn.xxm.mybatis.dict.helper.interceptor.HandleDictInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnClass({SqlSessionFactory.class})
@EnableConfigurationProperties({MybatisDictHelperProperties.class})
@Configuration
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
public class MybatisDictHelperAutoConfiguration {

    private final MybatisDictHelperProperties properties;

    public MybatisDictHelperAutoConfiguration(MybatisDictHelperProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public SysConfig sysConfig(SqlSessionFactory sqlSessionFactory) {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setCache(properties.isEnableCache());
        sysConfig.setExpire(properties.getExpire());

        HandlerDispatcher.setSysConfig(sysConfig);
        HandlerDispatcher.setSqlSessionFactory(sqlSessionFactory);

        return sysConfig;
    }

    @Bean
    public Interceptor dictHelperInterceptor() {
        return new HandleDictInterceptor();
    }
}
