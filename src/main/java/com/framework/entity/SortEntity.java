package com.framework.entity;

public interface SortEntity<PK> extends Comparable<SortEntity>, BaseEntity<PK> {
	   
	    Long getSortIndex();

	    void setSortIndex(Long sortIndex);

	    @Override
	    default int compareTo(SortEntity sortEntity) {
	        if (sortEntity == null) {
	            return -1;
	        }
	        
	        return Long.compare(getSortIndex() == null ? 0 : getSortIndex(), sortEntity.getSortIndex() == null ? 0 : sortEntity.getSortIndex());
	    }
}
