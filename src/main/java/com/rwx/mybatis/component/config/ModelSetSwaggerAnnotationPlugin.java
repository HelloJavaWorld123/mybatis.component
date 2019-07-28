package com.rwx.mybatis.component.config;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @Author：RXK
 * Date:2018/10/15 14:13
 * Version: V1.0.0
 * Des: 重写方法  在生成Mapper接口时，添加上@Resource注解
 **/
public class ModelSetSwaggerAnnotationPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		String apiModelAnnotation = "@Data";
		FullyQualifiedJavaType apiModelPackageType = new FullyQualifiedJavaType("lombok.Data");
		String otherAnon = "@EqualsAndHashCode(callSuper = true)";

		topLevelClass.addImportedType(apiModelPackageType);
		topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.EqualsAndHashCode"));
		topLevelClass.addAnnotation(apiModelAnnotation);
		topLevelClass.addAnnotation(otherAnon);
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}
}
