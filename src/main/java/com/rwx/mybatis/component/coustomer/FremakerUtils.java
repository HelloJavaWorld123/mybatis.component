package com.rwx.mybatis.component.coustomer;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Author：RXK
 * Date:2018/11/1 16:31
 * Version: V1.0.0
 * Des: 使用模板文件的工具
 **/
public class FremakerUtils {

    private static final Logger log = LoggerFactory.getLogger(FremakerUtils.class);

    public static Template getTemplate(String templateName){
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(new ClassTemplateLoader(FremakerUtils.class, "/template"));
        configuration.setCacheStorage(NullCacheStorage.INSTANCE);
        try {
            return configuration.getTemplate(templateName);
        } catch (IOException e) {
            log.info("获取模板时出现异常：",e.getMessage() );
            configuration.clearTemplateCache();
            return null;
        }
    }
}
