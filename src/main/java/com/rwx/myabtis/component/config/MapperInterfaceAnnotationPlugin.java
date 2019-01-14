package com.rwx.myabtis.component.config;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * Author：RXK
 * Date:2018/10/16 11:58
 * Version: V1.0.0
 * Des: 生成的Mapper Interface时 增加 @Resposity的注解
 **/
public class MapperInterfaceAnnotationPlugin extends PluginAdapter {
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		interfaze.addAnnotation("@Repository");
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
		interfaze.addImportedType(type);
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}
}
