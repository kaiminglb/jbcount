/** 

* @Title: CustomException.java

* @Package com.km.common.exception

* @Description: TODO(用一句话描述该文件做什么)

* @author hulikaimen@gmail.com

* @date 2018年1月21日 下午4:13:03

* @version V1.0 

*/ 
package com.km.common.exception;

import com.km.common.enums.ResultEnum;
import com.km.model.Result;

/**
 * @author PipiLu
 * @version 创建时间：2018年1月21日 下午4:13:03
 * 类说明  自定义异常
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 7833283455112352655L;
    private Integer code;
    
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }
    
    public CustomException(Integer code,String message) {
        super(message);
        this.code = code;
    }
    
    public CustomException(ResultEnum resultEnum){
    	super(resultEnum.getMsg());
    	this.code = resultEnum.getCode();
    }
    /**
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    protected CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    */
}
