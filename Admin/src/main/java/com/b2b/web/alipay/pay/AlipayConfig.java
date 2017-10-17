package com.b2b.web.alipay.pay;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088021765488964";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = "2088021765488964";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMkIrSE9Nr9G4v/Rw9sQQkauz1RTAEnpnxkjjKCnT2B5R8CI94/P0bPWuAyU6ZDWoRZInW3NascrvC8hrCZk1bV7PrQJPmNweGtwWELgLl96kPwjTJ4zqXuV413Pu54qsOVxrckyY3i+xHn05uSYrMYo4kQ9JWLUpyIFebp61KK1AgMBAAECgYAXdcu+dUZOTBdxgyk4B020sfHABW+jMIBAjsV6egvpVj3pWhKJ1dbDBeu0jCw1mtZxo0e76JztYU00stlEIcBOmCgGQmYt3EaM2cNA/q0p7v+dqv64XQPJ8n3SspZj0mYWCayrScCmtQMCLKEHHCSe2CmTcal4ten9an3KA6fKgQJBAP5P6RtrHZW92U5Az7GIFGUWSC500XPS4EaRouaVi4962QeGRZpNRMn51V4ECgNsOyEIQyW6uXNwc/JNXCeOIN8CQQDKXj5DGWxVObrTXC1+gqhmkMXPeW2PxCoYbnoX+WgAowV12J6xZOpk71s6qWxZ8oS2KfNTgM7jzQ/u6lfZpErrAkEA6yqEGgmsevrrBvTh2n5Gtc+sAeGeiid3o2orJGUngA+Ov+mHVY+cS9WOW/kKNW4RI5ObXDlWIozbezXUQPOqKwJBALKAjssBEd3bHkBxfm+pMIHIEbQAl/PIRJloWansBSEKqzZX7SLssRR3TTFhYIG2ufk0570wUU3/A57qdrMziCECQQCaFgDxZf/87y61QSLYhS2O58Leyyad3dl0g6hRICoVYhKsWJPDxNtG0JE17APvTNZo1Zvd2fhwQAsZIiPJwh17";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    
	public static String public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJCK0hPTa/RuL/0cPbEEJGrs9UUwBJ6Z8ZI4ygp09geUfAiPePz9Gz1rgMlOmQ1qEWSJ1tzWrHK7wvIawmZNW1ez60CT5jcHhrcFhC4C5fepD8I0yeM6l7leNdz7ueKrDlca3JMmN4vsR59ObkmKzGKOJEPSVi1KciBXm6etSitQIDAQAB";
 	
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://lingwa.com.cn/convenient/ali/shop_notify.do";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://lingwa.com.cn/convenient/ali/shop_return.htm";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "/home/alipaylogs";
		
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

