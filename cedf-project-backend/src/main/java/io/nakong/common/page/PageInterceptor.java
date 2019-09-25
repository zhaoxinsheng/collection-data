package io.nakong.common.page;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

/**
 * 分页 拦截器
 * xinsheng.zhao
 * 处理 按照 规定的方式进行分页
 */
@Component
// @Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
//@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class,Integer.class}
)})
public class PageInterceptor implements Interceptor {

    private static final Log log = LogFactory.getLog(PageInterceptor.class);

    private static String dialect = "mysql";
    private static String pageSqlId = ".*PageList.*";

    @Override
    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");

            // 拦截mybatis xml 文件中 id
            if (mappedStatement.getId().matches(pageSqlId)) {

                BoundSql boundSql = delegate.getBoundSql();
               // 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
                Object parameterObject = boundSql.getParameterObject();
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject尚未实例化！");
                }

                if (parameterObject instanceof Page || parameterObject instanceof Map) {	// 参数是PageSo实体
                    //pageV2支持传除了Page之外的参数进来,用来解决参数放在map中不可见的问题
                    Page<?> page = null;
                    if(parameterObject instanceof Page){
                        page = (Page<?>) parameterObject;
                    }else{
                        //从parameterMap中获取PageV2对象
                        @SuppressWarnings("unchecked")
                        Map<String,Object> parameterObjectMap = (Map<String,Object> )parameterObject;
                        for(Entry<String, Object> entry : parameterObjectMap.entrySet()){
                            if(entry.getValue() != null && entry.getValue() instanceof Page){
                                page = (Page<?>) entry.getValue();
                                break;
                            }
                        }
                    }
                    if (page == null || !page.isPaging()) {
                        return ivk.proceed();
                    }

                    int count = countForPageSql(ivk, mappedStatement, boundSql);
                    page.setTotal(count);

                    String pageSql = generatePageSql(boundSql.getSql(), page.getStart(), page.getEnd());
                    ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.

                } else {
                    throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在 page 属性！");
                }
            }
        }
        return ivk.proceed();
    }


    private int countForPageSql(Invocation ivk, MappedStatement mappedStatement, BoundSql boundSql) throws Throwable {
        Connection connection = (Connection) ivk.getArgs()[0];
        String sql = boundSql.getSql();
        String countSql = "select count(0) from (" + sql + ") temp"; // 记录统计
        log.debug(countSql);
        PreparedStatement countStmt = connection.prepareStatement(countSql);
        BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        Field metaParamsField = ReflectHelper.getFieldByFieldName(boundSql, "metaParameters");
        if (metaParamsField != null) {
            MetaObject mo = (MetaObject) ReflectHelper.getValueByFieldName(boundSql, "metaParameters");
            ReflectHelper.setValueByFieldName(countBS, "metaParameters", mo);
        }
        setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
        ResultSet rs = countStmt.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        countStmt.close();
        return count;
    }



    /**
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.
     * DefaultParameterHandler
     *
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException(
                                "There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    /**
     * 根据数据库方言，生成特定的分页sql
     *
     * @param sql
     * @param start
     * @param end
     * @return
     */
    private String generatePageSql(final String sql, final int start, final int end) {

        if (start < 0 || end < 0) {
            //start 必须从0开始，且start和stop不能为负数
            throw new IllegalArgumentException("Start / end cannot be negative for the Page Query!");
        }

        StringBuffer pageSql = new StringBuffer();

        //oracle 分页
        if ("oracle".equals(dialect)) {
            //注意：Oracle的行号从1开始，而应用程序的行号是统一从0开始，因此查询范围为(start+1)<= ROWNUM <= (end+1)
            pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
            pageSql.append(sql);
            pageSql.append(") tmp_tb where ROWNUM<=");
            pageSql.append((end + 1));
            pageSql.append(") where row_id>");
            pageSql.append(start);
            return pageSql.toString();
        }

        //mysql 分页
        if ("mysql".equals(dialect)) {
            pageSql.append(sql);
            //注意：MySQL的行号从0开始，而应用程序的行号是统一从0开始，因此查询范围为(start) <= ROWNUM <= (end), 即 limit (start), (end - start + 1)
            int offset = (start);
            int count = (end - start + 1);
            pageSql.append(" limit " + offset + "," + count);
            return pageSql.toString();
        }
        return sql;
    }

    @Override
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties arg0) {

    }

}
