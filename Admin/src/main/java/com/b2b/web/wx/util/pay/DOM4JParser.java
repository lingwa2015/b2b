/**
 * 作者:sh
 * 邮箱:545254680@qq.com
 * 时间:2015-9-22 上午10:10:43
 */
package com.b2b.web.wx.util.pay;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



/**
 * @author Administrator
 *
 */
public class DOM4JParser
{
	private static Logger logger= Logger.getLogger(DOM4JParser.class);
	/**
	 * 
	 * 说明:获取微信post/get过来的数据流
	 * 作者:hushsh
	 * 邮箱:452327322@qq.com
	 * 时间:2015-7-9 下午4:11:24
	 * 参数:
	 */
	public static Map<String,Object> getInputStream(HttpServletRequest request)
	{
		logger.info("======enter into DOM4JParser.getInputStream========");
		Map<String,Object> map = new HashMap<String,Object>();
	    InputStream inputStream;
		try
		{
			inputStream = request.getInputStream();
			 // 读取输入流
		    SAXReader reader = new SAXReader();
		    Document document = reader.read(inputStream);
		    // 得到xml根元素
		    Element root = document.getRootElement();
		    // 得到根元素的所有子节点
		    @SuppressWarnings("unchecked")
			List<Element> elementList = root.elements();
		    // 遍历所有子节点
		    for (Element e : elementList)
		    {
		    	map.put(e.getName(), e.getText());
		    }
		    logger.info("获取到的回调通知为(Get to the callback notification is):" + map);
			 // 释放资源
		    inputStream.close();
		    inputStream = null;
		}
		catch (Exception e1)
		{
			logger.error("err:" + e1);
			e1.printStackTrace();
		}
		return map;
	}
	
	
}
