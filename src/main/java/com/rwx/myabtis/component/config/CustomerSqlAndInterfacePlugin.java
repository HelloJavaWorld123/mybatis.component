package com.rwx.myabtis.component.config;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;

import java.util.List;

/**
 * Author：RXK
 * Date:2018/10/21 19:40
 * Version: V1.0.0
 * Des:
 **/
public class CustomerSqlAndInterfacePlugin extends PluginAdapter {
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}


	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		CustomerInterfaceGenerator generator = new CustomerInterfaceGenerator();
		generator.setContext(context);
		generator.setIntrospectedTable(introspectedTable);
		generator.addInterfaceElements(interfaze);
		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	/**
	 * 将新增的sql语句添加到 xml文件中
	 */
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		CustomerXmlElementGenerator elementGenerator = new CustomerXmlElementGenerator();
		elementGenerator.setIntrospectedTable(introspectedTable);
		elementGenerator.setContext(context);
		elementGenerator.addElements(document.getRootElement());
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

}
