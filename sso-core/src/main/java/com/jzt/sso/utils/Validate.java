package com.jzt.sso.utils;

import com.jzt.sso.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class Validate {
	
	public Validate falseThrow(boolean bool, String message){
		if(!bool) 
			throw new CustomException(message);
		return this;
	}
	
	public Validate trueThrow(boolean bool, String message){
		if(bool) 
			throw new CustomException(message);
		return this;
	}
	
	public Validate isNotEmpty(String str, String message){
		if(CommonUtils.empty(str))
			throw new CustomException(message);
		return this;
	}
	
	public Validate isNotEmpty(Object str, String message){
		if(CommonUtils.empty(str)) 
			throw new CustomException(message);
		return this;
	}
	
	public Validate isNotEmpty(Collection<?> collec, String message){
		if(CommonUtils.empty(collec)) 
			throw new CustomException(message);
		return this;
	}
	
	/**
	 * 非空， 且日期格式有误
	 * author lijuntao
	 * date 2018年5月16日
	 */
	public Validate isDate(String dateStr, String format, String message){
		if(CommonUtils.notEmpty(dateStr) && DateUtils.parse(dateStr, format) == null)
			throw new CustomException(message);
		return this;
	}
	


	public void error(String message) {
		throw new CustomException(message);
	}
	
	public void error(Exception e){
		if(e instanceof RuntimeException){
			throw (RuntimeException)e;
		}else{
			throw new CustomException(e);
		}
	}
	
	public void error(String message, Exception e){
		throw new CustomException(message, e);
	}

}
