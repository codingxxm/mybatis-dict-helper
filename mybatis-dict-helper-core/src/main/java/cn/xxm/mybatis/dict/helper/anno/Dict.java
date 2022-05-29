package cn.xxm.mybatis.dict.helper.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

    String table() default "";
    String property() default "";
    String column() default "";
    String text() default "";
    String condition() default "";

}
