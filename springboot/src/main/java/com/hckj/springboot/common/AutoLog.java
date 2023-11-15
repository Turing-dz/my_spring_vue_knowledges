package com.hckj.springboot.common;
import java.lang.annotation.*;
@Target(ElementType.METHOD)//指定注解可以应用的目标元素，这里是 ElementType.METHOD，表示该注解可以用于方法。
@Retention(RetentionPolicy.RUNTIME)//指定注解的生命周期,RetentionPolicy.RUNTIME 表示该注解会在运行时保留，这允许在运行时通过反射来访问注解信息
@Documented//指定了注解 AutoLog 包含在生成的 Javadoc 文档中
public @interface AutoLog {
    String value() default "";
}
