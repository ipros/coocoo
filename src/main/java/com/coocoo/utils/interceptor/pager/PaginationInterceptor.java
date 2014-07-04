package com.coocoo.utils.interceptor.pager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.coocoo.utils.ReflectUtil;
import com.coocoo.utils.interceptor.pager.dialect.Dialect;
import com.coocoo.utils.interceptor.pager.dialect.impl.MySQL5Dialect;
import com.coocoo.utils.interceptor.pager.dialect.impl.OrcaleDialect;
import com.coocoo.utils.interceptor.pager.dialect.impl.SqlServerDialect;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor implements Interceptor{
	private static final Log log = LogFactory.getLog(PaginationInterceptor.class);
	private Dialect.Type databaseType = null;
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
			RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
			StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(handler, "delegate");
	       BoundSql boundSql = handler.getBoundSql();
	       Object obj = boundSql.getParameterObject();
	       if(obj instanceof Pager<?>){
	    	   Pager<?> pager = (Pager<?>)obj;
	    	   MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");
	    	   System.out.println(mappedStatement);
	    	   Connection connection = (Connection)invocation.getArgs()[0];
	    	   String sql = boundSql.getSql();
	    	   this.setTotalRecord(pager, connection, mappedStatement);
	    	   Dialect dialect =null;
		       switch(databaseType){         
		           case MYSQL: dialect =new MySQL5Dialect();break;
		           case SQLSERVER : dialect = new SqlServerDialect();break;
		           case ORACLE : dialect = new OrcaleDialect();break;
		       }
		       String pageSql = dialect.getPaginationSql(sql, pager);
		       ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
		       System.out.println(boundSql.getSql());
	       }
	       return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		try{
			this.databaseType = Dialect.Type.valueOf(properties
                .getProperty("dialect").toUpperCase());
		}catch(Exception e){        
		         log.error("the value of the dialect property in mybatis-config.xml is not defined : "
		                  + properties.getProperty("dialect"))  ;
		}      
		
	}
	
	private void setTotalRecord(Pager<?> pager,Connection connection,MappedStatement mappedStatement){
		BoundSql boundSql = mappedStatement.getBoundSql(pager);
		String sql = boundSql.getSql();
		String countSql = this.getCountSql(sql);
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		BoundSql countBoundSql =new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, pager);
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, pager, countBoundSql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(countSql);
			parameterHandler.setParameters(ps);
			rs = ps.executeQuery();
			if(rs.next()){
				int totalRecord = rs.getInt(1);
				pager.setRowCount(totalRecord);
			}
		} catch (Exception e) {
			log.error(e.getMessage() + " --> get the totalRecord error!");
		}finally{
			try {
				if(rs != null ){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
	
	private String getCountSql(String sql){
		int index = sql.indexOf("from");
		return "select count(*) " +sql.substring(index);
	}

}
