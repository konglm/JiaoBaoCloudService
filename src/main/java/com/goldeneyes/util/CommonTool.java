/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：CommonTool
 * 文件功能描述：公共工具类
 *
 * 
 * 创建标识：konglm20161026
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author konglm
 *
 */
public class CommonTool {
	
	//测试用main函数
	public static void main(String[] args) {
		
	}
	
	//输出json字符串
	public static void outJsonString(HttpServletResponse response,String str) {
	    PrintWriter out = null;
	    try {
	    	response.setContentType("text/html;charset=utf-8");
	        out = response.getWriter();        
	        out.write(str);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally {
	        if (out != null) {
	            out.close();
	        }
	    }
	}

	
}
