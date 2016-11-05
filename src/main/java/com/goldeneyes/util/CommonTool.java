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

import javax.servlet.http.HttpServletRequest;
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
		System.out.println(getTotalPage(8,2));
	}

	public static int getTotalPage(int totalCnt, int pageSize) {
		if (totalCnt % pageSize == 0) {
			return totalCnt / pageSize;
		} else {
			return totalCnt / pageSize + 1;
		}
	}

	/**
	 * 输出json字符串
	 * 
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
	 * 
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
	 * 
	 * @param jArray
	 * @return
	 */
	public static JSONObject outJson(JSONObject jObject, String outResult) {
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("RspData", jObject);
		switch (outResult) {
		case "0000": {
			jsonobj.put("RspCode", "0000");
			jsonobj.put("RspTxt", "正常");
			break;
		}
		case "1001": {
			jsonobj.put("RspCode", "1001");
			jsonobj.put("RspTxt", "数据库连接异常");
			break;
		}
		case "1003": {
			jsonobj.put("RspCode", "1003");
			jsonobj.put("RspTxt", "参数类型错误");
			break;
		}
		case "1002": {
			jsonobj.put("RspCode", "1002");
			jsonobj.put("RspTxt", "数据库更新失败");
			break;
		}
		case "1004": {
			jsonobj.put("RspCode", "1004");
			jsonobj.put("RspTxt", "参数不全");
			break;
		}
		case "1005": {
			jsonobj.put("RspCode", "1005");
			jsonobj.put("RspTxt", "参数值为空");
			break;
		}
		case "1006": {
			jsonobj.put("RspCode", "1006");
			jsonobj.put("RspTxt", "分页相关参数值必须大于零");
			break;
		}
		case "1007": {
			jsonobj.put("RspCode", "1007");
			jsonobj.put("RspTxt", "上传档案失败");
			break;
		}
		case "1008": {
			jsonobj.put("RspCode", "1008");
			jsonobj.put("RspTxt", "Url被篡改");
			break;
		}
		case "1009": {
			jsonobj.put("RspCode", "1009");
			jsonobj.put("RspTxt", "令牌已过期或不存在");
			break;
		}
		}

		return jsonobj;
	}
	
	/**      
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException      
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**      
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException      
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

}
