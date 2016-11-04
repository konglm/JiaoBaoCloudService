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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author konglm
 *
 */
public class CommonTool {

	/**
	 * 测试用main函数
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * 输出json字符串
	 * @param response
	 * @param str
	 */
	public static void outJsonString(HttpServletResponse response, String str) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			out.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	/**
	 * Int转Byte
	 * @param res
	 * @return
	 */
	public static byte int2byte(int res) {
		byte[] targets = new byte[1];

		targets[0] = (byte) (res & 0xff);// 最低位
		return targets[0];
	}
	/**
	 * 封装JSON返回
	 * @param jArray
	 * @return
	 */
	public static JSONObject outJson(JSONObject jObject,String outResult){
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("RspData", jObject);
		switch(outResult){
			case "0000":{
				jsonobj.put("RspCode", "0000");
				jsonobj.put("RspTxt", "正常");
				break;
			}
			case "1001":{
				jsonobj.put("RspCode", "1001");
				jsonobj.put("RspTxt", "数据库连接异常");
				break;
			}
			case "1002":{
				jsonobj.put("RspCode", "1002");
				jsonobj.put("RspTxt", "参数类型错误");
				break;
			}
			case "1003":{
				jsonobj.put("RspCode", "1003");
				jsonobj.put("RspTxt", "数据库更新失败");
				break;
			}
			case "1004":{
				jsonobj.put("RspCode", "1004");
				jsonobj.put("RspTxt", "参数不全");
				break;
			}
			case "1005":{
				jsonobj.put("RspCode", "1005");
				jsonobj.put("RspTxt", "参数值为空");
				break;
			}
			case "1006":{
				jsonobj.put("RspCode", "1006");
				jsonobj.put("RspTxt", "上传失败");
				break;
			}
			case "1007":{
				jsonobj.put("RspCode", "1007");
				jsonobj.put("RspTxt", "Url被篡改");
				break;
			}
			case "1008":{
				jsonobj.put("RspCode", "1008");
				jsonobj.put("RspTxt", "令牌已过期或不存在");
				break;
			}
		}
		
		return jsonobj;
	}

}
