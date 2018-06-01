package com.framework.entity.param;

import java.util.ArrayList;
import java.util.List;

import com.framework.entity.TreeEntity;

public class TreeNode<E extends TreeEntity<PK>,PK> {
    private PK id;
    
    private String text;

    private E parent;
    
    
	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	//private E parent;
	

	private List<TreeNode<E,PK>> children;
	
/*	public E getParent() {
		return parent;
	}

	public void setParent(E parent) {
		this.parent = parent;
	}*/

	public List<TreeNode<E,PK>> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode<E,PK>> children) {
		this.children = children;
	}

}
