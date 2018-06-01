package com.framework.entity.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import com.framework.entity.TreeEntity;

/**
 * t_sys_depart
 * @author 
 */
@Table(name="t_sys_depart")
public class Depart implements TreeEntity<Long> {
	@Id
    private Long id;

    private Long pid;

    private String code;

    private String name;

    private Short level;

    private String levelcode;

    private Short sortno;

    private String remark;

    private Date createtime;

    private static final long serialVersionUID = 1L;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getLevelcode() {
        return levelcode;
    }

    public void setLevelcode(String levelcode) {
        this.levelcode = levelcode;
    }

    public Short getSortno() {
        return sortno;
    }

    public void setSortno(Short sortno) {
        this.sortno = sortno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

  	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getParentId() {
		
		return this.pid;
	}

	@Override
	public void setParentId(Long parentId) {
		this.pid = parentId;
		
	}

	@Override
	public <T extends TreeEntity> List<T> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}
}