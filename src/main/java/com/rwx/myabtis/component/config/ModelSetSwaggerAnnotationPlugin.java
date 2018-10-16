package com.rwx.myabtis.component.config;

import io.swagger.annotations.ApiModel;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Author：RXK
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
		String apiModelAnnotation = "@ApiModel(value = \""+introspectedTable.getTableConfiguration().getDomainObjectName()+"\")";
		String modelPackage = properties.getProperty("swaggerApiModelPackage", "io.swagger.annotations.ApiModel");
		FullyQualifiedJavaType apiModelPackageType = new FullyQualifiedJavaType(modelPackage);
		topLevelClass.addImportedType(apiModelPackageType);
		topLevelClass.addAnnotation(apiModelAnnotation);
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		String propertyAnnotation = "@ApiModelProperty(dataType = \""+introspectedColumn.getJdbcTypeName()+"\",notes = \""+introspectedColumn.getRemarks()+"\")";
		//自定义导入包
		String propertyPackage = properties.getProperty("swaggerApiModelPropertyPackage");

		if(StringUtils.isEmpty(propertyPackage)){
			propertyPackage = "io.swagger.annotations.ApiModelProperty";
		}

		FullyQualifiedJavaType apiModelPropertyType = new FullyQualifiedJavaType(propertyPackage);
		//导入包路径
		topLevelClass.addImportedType(apiModelPropertyType);
		//增加字段的注解
		field.addAnnotation(propertyAnnotation);
		return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
	}

}
