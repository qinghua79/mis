package com.framework.controller.sys;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.controller.BaseController;
import com.framework.controller.message.Tree;
import com.framework.entity.param.TreeNode;
import com.framework.entity.sys.Depart;
import com.framework.service.sys.DepartService;



@Controller
@RequestMapping("/depart")
public class DepartController extends BaseController {
         
	    @Autowired
	    private DepartService departService;
	
	    @RequestMapping(value = "/tree", method = RequestMethod.GET)
	    @ResponseBody
	    public List<Tree> tree() {
	    	List<Tree> treeList = new ArrayList<>();
	    	List<Depart> departList =  departService.selectAll();
	    	List<Depart> children = new ArrayList<>();
	    	
	    	children = getChildren(null, departList);
	    	wrapTree(children, departList, treeList);
	        return treeList;
	    }
	    
	    private List<Depart> getChildren(Long parentId, List<Depart> departList){
	    	List<Depart> children = new ArrayList<Depart>();
	    	for (Depart depart : departList){
	    		if (parentId == depart.getParentId()) {
	    			children.add(depart);
	    		}
	    	}
	    	return children;
	    }
	    
	    private void wrapTree(List<Depart> children,List<Depart> departList, List<Tree> treeList){
	    	for (Depart depart :children){
	    		Tree tree = new Tree();
	    		tree.setId(depart.getId());
	    		tree.setText(depart.getName());
	    		Map<Object, Object> attributes = new HashMap<Object, Object>();
	    		attributes.put("level", depart.getLevel());
	    		attributes.put("levelCode", depart.getLevelcode());
	    		tree.setAttributes(attributes);
	    		treeList.add(tree);
	    		//获取子孩子
	    		List<Tree> treeChildren = new ArrayList<>();
	    		List<Depart> children2 =  getChildren(depart.getId(),departList);
	    		wrapTree(children2, departList, treeChildren);
	    		tree.setChildren(treeChildren);
	    	} 
	    }
	    
	  
	    
}
