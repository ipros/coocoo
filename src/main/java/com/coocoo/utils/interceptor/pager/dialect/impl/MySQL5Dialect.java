package com.coocoo.utils.interceptor.pager.dialect.impl;

import com.coocoo.utils.interceptor.pager.Pager;
import com.coocoo.utils.interceptor.pager.dialect.Dialect;

public class MySQL5Dialect implements Dialect{

	@Override
	public String getPaginationSql(String sql, Pager<?> pager) {
		return sql + " limit " + (pager.getPageNo()-1)*pager.getPageSize() + "," + pager.getPageSize();
	}

}
