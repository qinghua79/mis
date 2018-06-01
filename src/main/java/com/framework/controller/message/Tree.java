package com.framework.controller.message;

import java.util.List;
import java.util.Map;

public class Tree {
	private Long id;
	private Long pid;
	private String text;

	// 节点状态， 'open' 或 'closed'，默认是 'open'。当设为 'closed' 时，此节点有子节点，并且将从远程站点加载它们
	private String state;  
	
	private Boolean checked; //带checkbox的树，指示节点是否被选中。
	private boolean editable = true;//带checkbox的树，指示节点是否可以修改
	private String iconCls;
	private String openMode;
	private Map<Object, Object> attributes;

	private List<Tree> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public  Map<Object, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes( Map<Object, Object> attributes) {
		this.attributes = attributes;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
