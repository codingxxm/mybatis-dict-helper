package cn.codingxxm.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class GenerateMybatis {



    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&serverTimezone=Asia/Shanghai&allow", "root", "111111")
                .globalConfig(builder -> {
                    builder.author("xiaoming") // 设置作者
                            .outputDir("/Users/xiaoming/Documents/git/mybatis-dict-helper/demo/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.test") // 设置父包名
                            .moduleName("mybatis") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/xiaoming/Documents/git/mybatis-dict-helper/demo/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user", "dict"); // 设置需要生成的表名
//                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
