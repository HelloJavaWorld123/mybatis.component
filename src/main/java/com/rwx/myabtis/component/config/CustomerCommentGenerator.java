package com.rwx.myabtis.component.config;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * Author：RXK
 * Date:2018/10/15 21:24
 * Version: V1.0.0
 * Des: 自定义生成domin时字段的注释
 **/
public class CustomerCommentGenerator extends DefaultCommentGenerator {

	/**
	 * 修改Mapper接口中的方法的注释
	 */
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

	}

	/**
	 * 去除生成xml时的原生注释
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
	}

	/**
	 * get set方法 不需要注释时 无需返回
	 */
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		StringBuffer sb = new StringBuffer();
		field.addJavaDocLine("/**");
		field.addJavaDocLine("*");
		sb.append("* TableName: "+introspectedTable.getFullyQualifiedTable()+"\n\t");
		sb.append("* ColumnName: "+introspectedColumn.getActualColumnName()+"\n\t");
		sb.append("*<pre>"+"\n\t");
		sb.append("*"+introspectedColumn.getRemarks());
		field.addJavaDocLine(sb.toString());
		field.addJavaDocLine("*</pre>");
		field.addJavaDocLine("**/");
	}
}
