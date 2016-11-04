/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：BusinessException
 * 文件功能描述：自定义异常
 *
 * 
 * 创建标识：konglm20161104
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.vo;

/**
 * @author konglm
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public BusinessException(Object Obj) {
        super(Obj.toString());
    }
    
    public BusinessException() {
        super();
    }
}
