package com.framework.core.common;

public interface IDGenerator<T> {
 	 T generate();
     IDGenerator<String> UUID = java.util.UUID.randomUUID()::toString;
}
