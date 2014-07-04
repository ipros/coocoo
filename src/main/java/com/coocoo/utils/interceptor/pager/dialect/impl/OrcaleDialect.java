package com.coocoo.utils.interceptor.pager.dialect.impl;

import com.coocoo.utils.interceptor.pager.Pager;
import com.coocoo.utils.interceptor.pager.dialect.Dialect;

public class OrcaleDialect implements Dialect {

	@Override
	public String getPaginationSql(String sql, Pager<?> pager) {
		return "select * from (select rownum rn, t.* from (" + sql
	              + ") t where rownum <= " + (pager.getPageNo()* pager.getPageSize())
	              + ") t1 where t1.rn > " + ((pager.getPageNo()- 1) * pager.getPageSize());
	}



}
