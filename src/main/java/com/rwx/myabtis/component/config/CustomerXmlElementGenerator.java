package com.rwx.myabtis.component.config;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/**
 * Author：RXK
 * Date:2018/10/21 17:55
 * Version: V1.0.0
 * Des: 扩展生成的xml文件
 * 添加自定义的sql语句
 * 后期优化 sql语句
 **/
public class CustomerXmlElementGenerator extends AbstractXmlElementGenerator {
	@Override
	public void addElements(XmlElement parentElement) {
		//添加自定义的where筛选条件
		XmlElement whereElement = new XmlElement("sql");
		whereElement.addAttribute(new Attribute("id", "base_query"));

		XmlElement selectElement = new XmlElement("trim");
		selectElement.addAttribute(new Attribute("prefix","WHERE"));
		selectElement.addAttribute(new Attribute("prefixOverrides", "AND | OR"));

		StringBuilder sb  = new StringBuilder();
		//遍历所有的字段 作为筛选条件
		for(IntrospectedColumn column : introspectedTable.getAllColumns()){
			XmlElement notNullConditionElement = new XmlElement("if");
			sb.setLength(0);
			sb.append("null!=");
			sb.append(column.getJavaProperty());
			notNullConditionElement.addAttribute(new Attribute("test", sb.toString()));

			sb.setLength(0);
			sb.append("and");
			sb.append(" record.");
			sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(column));
			sb.append("=");
			sb.append(MyBatis3FormattingUtilities.getParameterClause(column));
			notNullConditionElement.addElement(new TextElement(sb.toString()));
			//添加到trim标签下面
			selectElement.addElement(notNullConditionElement);
		}
		whereElement.addElement(selectElement);
		parentElement.addElement(whereElement);

		//增加查询所有的 xml 组件
		sb.setLength(0);
		sb.append("SELECT ").append("record.*").append(" FROM ").append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime())
				.append(" record");
		TextElement selectAllComponent = new TextElement(sb.toString());

		XmlElement includeXmlElement = new XmlElement("include");
		includeXmlElement.addAttribute(new Attribute("refid", "base_query"));

		//增加自定义的方法 查询所有的方法
		XmlElement selectAll = new XmlElement("select");
		selectAll.addAttribute(new Attribute("id", "selectAllByRecord"));
		selectAll.addAttribute(new Attribute("resultMap", "BaseResultMap"));
		selectAll.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));

		selectAll.addElement(selectAllComponent);
		selectAll.addElement(includeXmlElement);
		parentElement.addElement(selectAll);
	}
}
