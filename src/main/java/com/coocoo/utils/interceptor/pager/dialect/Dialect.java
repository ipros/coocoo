package com.coocoo.utils.interceptor.pager.dialect;

import com.coocoo.utils.interceptor.pager.Pager;

public interface Dialect {
	public static enum Type {
	       MYSQL {
	           public String getValue(){return "mysql";}
	       },
	       SQLSERVER {
	           public String getValue() {return "sqlserver";}
	       },
	       ORACLE {
	           public String getValue() {return "oracle";}
	       };
	       public abstract String getValue();
	    }
	   
	    /**
	     * 获取分页sql
	     * @param sql 原始查询sql
	     * @param offset 开始记录索引（从0开始计数）
	     * @param limit 每页记录大小
	     * @return 数据库相关的分页sql
	     */
	    public String getPaginationSql(String sql,  Pager<?> pager);
}
