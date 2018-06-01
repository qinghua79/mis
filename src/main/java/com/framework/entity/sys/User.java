package com.framework.entity.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.framework.entity.BaseEntity;

/**
 * t_sys_user
 * @author 
 */

@Table(name="t_sys_user")
public class User implements BaseEntity<Long> {
    /**
     * 用户ID 
     */
	@Id
    private Long id;

    /**
     * 用户姓名 
     */
    private String username;

    /**
     * 登陆名 
     */
    private String loginname;

    /**
     * 密码 
     */
    private String password;

    private Integer sex;

    private Integer usertype;

    /**
     * 最后登入系统机构
     */
    private Integer departid;

    /**
     * 用户状态 
     */
    private Integer status;

    /**
     * 排序号 
     */
    private Integer sortno;

    /**
     * 备注 
     */
    private String remark;

    private Date createtime;

    private static final long serialVersionUID = 1L;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
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
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getLoginname() == null ? other.getLoginname() == null : this.getLoginname().equals(other.getLoginname()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getUsertype() == null ? other.getUsertype() == null : this.getUsertype().equals(other.getUsertype()))
            && (this.getDepartid() == null ? other.getDepartid() == null : this.getDepartid().equals(other.getDepartid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getSortno() == null ? other.getSortno() == null : this.getSortno().equals(other.getSortno()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getLoginname() == null) ? 0 : getLoginname().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getUsertype() == null) ? 0 : getUsertype().hashCode());
        result = prime * result + ((getDepartid() == null) ? 0 : getDepartid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getSortno() == null) ? 0 : getSortno().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", loginname=").append(loginname);
        sb.append(", password=").append(password);
        sb.append(", sex=").append(sex);
        sb.append(", usertype=").append(usertype);
        sb.append(", departid=").append(departid);
        sb.append(", status=").append(status);
        sb.append(", sortno=").append(sortno);
        sb.append(", remark=").append(remark);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}
}