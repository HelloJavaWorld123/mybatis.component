package com.rwx.myabtis.component.config.interceptor;

import com.rwx.myabtis.component.common.MyReflectUtils;
import com.rwx.myabtis.component.common.PageDTO;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * author: RXK
 * date: 2019/3/28 14:16
 * version: v1.0.0
 * desc: MyBatis 分页处理 拦截器
 * 在Mybatis 的配置文件中 需要将该拦截器设置到 plugin 标签
 **/
@Intercepts(
        @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class })
)
public class PageInterceptor implements Interceptor {

    private static ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();

    private static ObjectWrapperFactory DEFAULT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    private static ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    //执行统计sql时 通过反射(MetaObject)获取到的ParameterHandler 然后再
    private static String DELEGATE_PARAMETER_HANDLER = "delegate.parameterHandler";

    private static String DELEGATE_BOUNDSQL_SQL = "delegate.boundSql.sql";

    private static final Logger LOGGER = LoggerFactory.getLogger(PageInterceptor.class);

    /**
     * 拦截逻辑的实现
     * 1 需要执行的方法中是否有 分页的参数
     * 2 有的 再重新改造sql 语句
     * 3 否则 直接返回
     */
    @Override public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        //当前执行的sql语句 以及 接口中的参数
        BoundSql boundSql = statementHandler.getBoundSql();
        //获取当前的接口 方法中的参数
        Object parameterObject = boundSql.getParameterObject();

        //如果有 分页的参数
        if (parameterObject instanceof PageDTO) {

            MetaObject metaObject = MetaObject
                    .forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_WRAPPER_FACTORY,
                            DEFAULT_REFLECTOR_FACTORY);

            Connection connection = (Connection) invocation.getArgs()[0];

            PageDTO dto = (PageDTO) parameterObject;

            //重新构造sql语句
            reBuildSQL(metaObject,boundSql, dto);

            //在当前的条件下查询 总的记录数
            setTotalRecord(metaObject,boundSql, connection, dto);
        }
        return invocation.proceed();
    }

    private static void setTotalRecord(MetaObject metaObject, BoundSql boundSql,
            Connection connection, PageDTO dto) {
        //原始的sql 语句
        String originalSQL = boundSql.getSql();
        String countSql = "SELECT COUNT(1) FROM ( " + originalSQL + ")a ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue(DELEGATE_PARAMETER_HANDLER);

            parameterHandler.setParameters(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                dto.setTotalNum(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            LOGGER.info("执行统计数量的查询语句 出现异常：{}", e);
        }
    }

    //重新构建 sql 语句
    private void reBuildSQL(MetaObject metaObject, BoundSql boundSql, PageDTO dto) {
        String sql = boundSql.getSql();
        String pageSql = pageSQL(sql, dto);
        metaObject.setValue(DELEGATE_BOUNDSQL_SQL,pageSql);
    }

    //重新构建sql
    private String pageSQL(String sql, PageDTO dto) {
        return sql + "LIMIT " + getOffset(dto) + " , " + dto.getPageSize();
    }

    //获取偏移量
    private int getOffset(PageDTO dto) {
        return (dto.getPageNo() - 1) * dto.getPageSize();
    }

    /**
     * 针对 需要拦截的目标对象，否则直接返回对象
     *
     * @param target : 需要拦截的对象
     */
    @Override public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * 运行该 拦截器之前 先运行该方法
     *
     * @param properties ：
     */
    @Override public void setProperties(Properties properties) {

    }
}
