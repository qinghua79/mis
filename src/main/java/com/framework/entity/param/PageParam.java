package com.framework.entity.param;

public class PageParam<T>  {

	private int pageNum = 1;
	
	private int pageSize = 10;
	
	private String orderBy;
	
	private T condition;
	
	public PageParam(int pageNum, int pageSize){
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	public int getOffset() {
		return (this.pageNum - 1) * this.pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public T getCondition() {
		return condition;
	}

	public void setCondition(T condition) {
		this.condition = condition;
	}
	
	
}  

