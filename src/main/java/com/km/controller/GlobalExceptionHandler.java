/** 

* @Title: GlobalExceptionHandler.java

* @Package handle

* @Description: TODO(用一句话描述该文件做什么)

* @author hulikaimen@gmail.com

* @date 2018年1月21日 下午4:05:49

* @version V1.0 

*/ 
package com.km.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.km.common.ResultUtils;
import com.km.common.exception.CustomException;
import com.km.model.Result;

/**
 * @author PipiLu
 * @version 创建时间：2018年1月21日 下午4:05:49
 * 类说明 全局异常处理类。必须让springmvc扫描到，所以和controller放一个包
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//返回json对象
    @ExceptionHandler(value=CustomException.class)//处理指定类型异常
    public @ResponseBody Result handleCustomException(CustomException e){
        //调用commons-lang3下的ExceptionUtils 类来获取异常的具体信息
    	logger.error(ExceptionUtils.getStackTrace(e));
    	return ResultUtils.error(e.getCode(),e.getMessage());
    }

    /**
	//其他类型异常
    @ExceptionHandler(value = OtherException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception ex) {
        // 其他异常处理逻辑...
    }
	*/
}
