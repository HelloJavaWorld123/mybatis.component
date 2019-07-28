package com.rwx.mybatis.component.config;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * @author : RXK
 * @version : v1.0.0
 * @Date : 2019/7/28 17:30
 * @Des:
 */
public class CustomerJavaTypeResolver extends JavaTypeResolverDefaultImpl {

	public CustomerJavaTypeResolver() {
		super();
		typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", new FullyQualifiedJavaType(Integer.class.getName())));
		typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT",new FullyQualifiedJavaType(Integer.class.getName())));
		typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
		typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB",new FullyQualifiedJavaType(String.class.getName())));
	}
}
