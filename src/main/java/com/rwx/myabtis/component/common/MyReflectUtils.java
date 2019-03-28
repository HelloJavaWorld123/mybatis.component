package com.rwx.myabtis.component.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * author: RXK
 * date: 2019/3/28 16:46
 * version: v1.0.0
 * desc: 自定义反射工具类
 **/
public class MyReflectUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyReflectUtils.class);

    public static Object setFieldValue(Object target,String fieldName,Object value){
       Field field =  getField(target,fieldName);
        if (Objects.nonNull(field)) {
            field.setAccessible(true);
            try {
                field.set(target,value);
            } catch (IllegalAccessException e) {
                LOGGER.info("通过反射设置属性值出现异常{}",e);
                return null;
            }
        }
        return field;
    }

    private static Field getField(Object target, String fieldName) {
        return ReflectionUtils.findField(target.getClass(), fieldName);
    }

}
