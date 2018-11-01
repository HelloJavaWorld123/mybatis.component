package com.rwx.mybatis.component.config;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

/**
 * Author：RXK
 * Date:2018/10/21 18:39
 * Version: V1.0.0
 * Des: 自定义生成的interface 接口文件
 * 继承一个基类
 * 后期优化
 **/
public class CustomerInterfaceGenerator extends AbstractJavaMapperMethodGenerator {

	@Override
	public void addInterfaceElements(Interface interfaze) {
		//添加集合方法
		addInterfaceList(interfaze);
	}

	private void addInterfaceList(Interface interfaze) {
		//整个类文件需要导入的java 类型
		Set<FullyQualifiedJavaType> importTypes = new TreeSet<>();
		importTypes.add(FullyQualifiedJavaType.getNewListInstance());

		//创建一个方法
		Method customMethod = new Method();
		customMethod.setVisibility(JavaVisibility.PUBLIC);

		//设置方法的返回类型
		FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
		//设置List的泛型是实体的类型
		FullyQualifiedJavaType listType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		//在接口文件中导入实体类的包
		importTypes.add(listType);
		returnType.addTypeArgument(listType);

		customMethod.setReturnType(returnType);
		customMethod.setName("selectAllByRecord");

		//方法中的参数类型
		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		importTypes.add(parameterType);

		customMethod.addParameter(new Parameter(parameterType,"record" ));

		context.getCommentGenerator().addGeneralMethodComment(customMethod, introspectedTable);

		if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(customMethod, interfaze, introspectedTable)) {
			interfaze.addImportedTypes(importTypes);
			interfaze.addMethod(customMethod);
		}
	}
}
