
package com.framework.entity.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;



@ApiModel(description = "分页结果")
public class PagerResult<E> implements Serializable {
    private static final long serialVersionUID = -6171751136953308027L;

    public static <E> PagerResult<E> empty(){
        return new PagerResult<>(0, Collections.emptyList());
    }

    public static <E> PagerResult<E> of(int total,List<E> list){
        return new PagerResult<>(total,list);
    }
    private long total;

    private List<E> rows;

    public PagerResult() {
    }

    public PagerResult(long total, List<E> rows) {
        this.total = total;
        this.rows = rows;
    }

    @ApiModelProperty("数据总数量")
    public long getTotal() {
        return total;
    }

    public PagerResult<E> setTotal(long total) {
        this.total = total;
        return this;
    }

    @ApiModelProperty("查询结果")
    public List<E> getRows() {
        return rows;
    }

    public PagerResult<E> setRows(List<E> rows) {
        this.rows = rows;
        return this;
    }

}
