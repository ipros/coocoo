package com.coocoo.utils.interceptor.pager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Pager<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
		public static final Pager DEFAULT = new Pager();
	//默认的每页记录数
	    public static final int DEFAULT_PAGE_SIZE = 20;
	    // 每页的记录数
	    private int pageSize = DEFAULT_PAGE_SIZE;
	    // 当前页
	    private int pageNo = 1;
	    // 总行数
	    private int rowCount;
	    // 总页数
	    private int pageCount;
	    // 每页的记录
	  // private List<T> resultList;
	  //其 他的参数Map对象 
	    private Map<String, Object> params = new HashMap<String, Object>();
	   
	    public Pager() {

	    }
	   
	    public Pager(int pageNo) {
	       this.pageNo = pageNo;
	    }
	 
	    public Pager(int pageSize, int pageNo) {
	       this.pageSize = pageSize;
	       this.pageNo = pageNo;
	    }
	 
	    public int getPageSize() {
	       return pageSize;
	    }
	 
	    public void setPageSize(int pageSize) {
	       this.pageSize = pageSize;
	    }
	 
	    public int getPageNo() {
	       return pageNo;
	    }
	 
	    public void setPageNo(int pageNo) {
	       this.pageNo = pageNo;
	    }
	 
	    public int getRowCount() {
	       return rowCount;
	    }
	 
	    public void setRowCount(int rowCount) {
	       this.rowCount = rowCount;
	       if(rowCount % pageSize == 0) {
	           this.pageCount = rowCount / pageSize;
	       } else {
	           this.pageCount = rowCount / pageSize + 1;
	       }
	    }
	   
	    public int getPageCount() {
	       return pageCount;
	    }
	   
	    public void setPageCount(int pageCount) {
	       this.pageCount = pageCount;
	    }
	 
//	    public List<T> getResultList() {
//	       return resultList;
//	    }
//	 
//	    public void setResultList(List<T> resultList) {
//	       this.resultList = resultList;
//	    }

		public Map<String, Object> getParams() {
			return params;
		}

		public void setParams(Map<String, Object> params) {
			this.params = params;
		}
	    
	    
}
