package com.itheima.reggie.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Intercepts({
    @Signature(
        type = Executor.class,
        method = "query",
        args = {
            MappedStatement.class,
            Object.class,
            RowBounds.class,
            ResultHandler.class
        }
    ),
    @Signature(
        type = Executor.class,
        method = "update",
        args = {
            MappedStatement.class,
            Object.class
        }
    )
})
@Slf4j
public class SqlPrintInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        Object parameterObject = invocation.getArgs().length > 1
                ? invocation.getArgs()[1]
                : null;

        BoundSql boundSql = ms.getBoundSql(parameterObject);
        Configuration configuration = ms.getConfiguration();

        String sql = buildSql(configuration, boundSql);

        log.info("【SQL】：{}" , sql);

        return invocation.proceed();
    }
    private String buildSql(Configuration configuration, BoundSql boundSql) {

        String sql = boundSql.getSql().replaceAll("\\s+", " ");

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();

        if (parameterMappings == null || parameterMappings.isEmpty()) {
            return sql;
        }

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        MetaObject metaObject = configuration.newMetaObject(parameterObject);

        for (ParameterMapping pm : parameterMappings) {
            String propertyName = pm.getProperty();
            Object value;

            if (boundSql.hasAdditionalParameter(propertyName)) {
                value = boundSql.getAdditionalParameter(propertyName);
            } else if (parameterObject == null) {
                value = null;
            } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                value = parameterObject;
            } else {
                value = metaObject.getValue(propertyName);
            }

            sql = sql.replaceFirst("\\?", formatValue(value));
        }

        return sql;
    }
    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof LocalDateTime) {
            return "'" + value.toString().replace('T', ' ') + "'";
        }
        if (value instanceof Date) {
            return "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value) + "'";
        }
        return value.toString();
    }



}