package com.rwx.mybatis.component.config;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Document;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Author：RXK
 * Date:2018/10/19 13:07
 * Version: V1.0.0
 * Des:
 **/
public class SqlMapperGenertorPlugin extends PluginAdapter {
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}


	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		try {
			Field field = sqlMap.getClass().getDeclaredField("isMergeable");
			field.setAccessible(true);
			field.setBoolean(sqlMap, false);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return true;
	}
}
