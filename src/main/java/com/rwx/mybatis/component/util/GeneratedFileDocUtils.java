package com.rwx.mybatis.component.util;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.dom.OutputUtilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author：RXK
 * Date:2018/11/9 17:08
 * Version: V1.0.0
 * Des: 自定义生成的文件的注释内筒
 **/
public class GeneratedFileDocUtils {
    public static String setJavaFileDoc(String author) {
        StringBuilder sb = new StringBuilder();
        sb.append("/**");
        OutputUtilities.newLine(sb);
        sb.append("*@author : "+ (StringUtils.isEmpty(author) ? "system" : author));
        OutputUtilities.newLine(sb);
        sb.append("*@Date ："+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        OutputUtilities.newLine(sb);
        sb.append("*@Version: V1.0.0");
        OutputUtilities.newLine(sb);
        sb.append("* Des ：");
        OutputUtilities.newLine(sb);
        sb.append("*/");
        return sb.toString();
    }
}
