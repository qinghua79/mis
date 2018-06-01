package com.framework.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.framework.core.common.MultipleDateEditor;
import com.framework.core.common.SessionObject;
import com.framework.core.util.SpringUtils;
/**
 * @author: zhengqh
 * @date: 2018-05-02
 * 
 * 1.加入字典获取
 * 2.加入多国语言支持
 * 
 * 
 **/



@Controller
public abstract class BaseController {
	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 * @throws IOException
	 * @throws ServletException
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
		// 日期处理  
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// dateFormat.setLenient(false);
		// binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, new MultipleDateEditor("yyyy-MM-dd HH:mm:ss", new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd" }, true));
		// String类型会被Trim
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	    
	}
	
	/**
	 * 获取当前用户的 SessionObject
	 * 
	 * @return
	 */
	protected SessionObject getSessionObject() {
		return SpringUtils.get(SessionObject.BEAN_ID, SpringUtils.SCOPE_SESSION);
	}
	
	//@RequestMapping("/getCodeInfo")
	//@ResponseBody
	//public List<CodeInfo> getCodeInfo(String codeTypeId){
	//	return null;
		//* return CacheUtil.getCodeInfoListByCodeTypeId(codeTypeId);
    //}
	
	
   
    public <M,E> E modelToEntity(M model, E entity) {
        BeanUtils.copyProperties(model, entity);
        return entity;
    }
	
}
