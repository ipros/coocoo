package com.coocoo.utils.interceptor.pager.dialect.impl;

import com.coocoo.utils.interceptor.pager.Pager;
import com.coocoo.utils.interceptor.pager.dialect.Dialect;

public class SqlServerDialect implements Dialect {

	@Override
	public String getPaginationSql(String sql, Pager<?> pager) {
		return "select top " + pager.getPageSize() + " from (" + sql
	              + ") t where t.id not in (select top " + (pager.getPageNo()-1)*pager.getPageSize() + " t1.id from ("
	              + sql + ") t1)";
	}



}
