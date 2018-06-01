package com.framework.dao;

public interface CrudMapper<T> extends
InsertMapper<T>,
DeleteMapper<T>,
UpdateMapper<T>,
QueryMapper<T> {

}
