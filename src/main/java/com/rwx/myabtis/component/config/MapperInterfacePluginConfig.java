package com.rwx.myabtis.component.config;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * Author：RXK
 * Date:2018/10/15 14:13
 * Version: V1.0.0
 * Des: 重写方法  在生成Mapper接口时，添加上@Resource注解
 **/
public class MapperInterfacePluginConfig extends PluginAdapter {
	@Override
	public boolean validate(List<String> warnings) {
		return false;
	}
}
