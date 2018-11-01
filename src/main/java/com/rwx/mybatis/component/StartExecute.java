package com.rwx.mybatis.component;

import org.apache.log4j.Logger;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：RXK
 * Date:2018/10/15 11:54
 * Version: V1.0.0
 * Des: 生成指定文件的启动类
 **/
public class StartExecute {

	private static final Logger LOGGER = Logger.getLogger(StartExecute.class);

	public static void main(String[] args) {
		List<String> warnings = new ArrayList<String>();
		try {
			//重新生成时 代码是否被覆盖
			boolean overwrite = true;
			//加载MyBatis的xml配置文件
			File file = new File(StartExecute.class.getClassLoader().getResource("common/generator.xml").getFile());

			ConfigurationParser parser = new ConfigurationParser(warnings);
			Configuration configuration = parser.parseConfiguration(file);

			DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, defaultShellCallback, warnings);
			myBatisGenerator.generate(null);

		} catch (IOException | SQLException | InterruptedException | InvalidConfigurationException | XMLParserException e) {
			LOGGER.info("出现的异常信息是{"+e.getMessage()+"}");
		}

		warnings.forEach(item ->System.out.println());
		System.out.println("生成完毕");
	}

}
