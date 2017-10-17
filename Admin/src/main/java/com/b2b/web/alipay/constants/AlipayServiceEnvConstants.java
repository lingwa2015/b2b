
/**

 * Alipay.com Inc.

 * Copyright (c) 2004-2014 All Rights Reserved.

 */

package com.b2b.web.alipay.constants;

/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49
 *          taixu.zqq Exp $
 */
public class AlipayServiceEnvConstants {

	/** 支付宝公钥-从支付宝服务窗获取 */
	public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

	/** 签名编码-视支付宝服务窗要求 */
	public static final String SIGN_CHARSET = "GBK";

	/** 字符编码-传递给支付宝的数据编码 */
	public static final String CHARSET = "GBK";

	/** 签名类型-视支付宝服务窗要求 */
	public static final String SIGN_TYPE = "RSA";

	public static final String PARTNER = "2088021765488964";

	/** 服务窗appId */
	// TODO !!!! 注：该appId必须设为开发者自己的服务窗id 这里只是个测试id
	public static final String APP_ID = "2016081001729960";

	// 开发者请使用openssl生成的密钥替换此处
	// 请看文档：https://fuwu.alipay.com/platform/doc.htm#2-1接入指南
	// TODO !!!! 注：该私钥为测试账号私钥 开发者必须设置自己的私钥 , 否则会存在安全隐患
	public static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMkIrSE9Nr9G4v/Rw9sQQkauz1RTAEnpnxkjjKCnT2B5R8CI94/P0bPWuAyU6ZDWoRZInW3NascrvC8hrCZk1bV7PrQJPmNweGtwWELgLl96kPwjTJ4zqXuV413Pu54qsOVxrckyY3i+xHn05uSYrMYo4kQ9JWLUpyIFebp61KK1AgMBAAECgYAXdcu+dUZOTBdxgyk4B020sfHABW+jMIBAjsV6egvpVj3pWhKJ1dbDBeu0jCw1mtZxo0e76JztYU00stlEIcBOmCgGQmYt3EaM2cNA/q0p7v+dqv64XQPJ8n3SspZj0mYWCayrScCmtQMCLKEHHCSe2CmTcal4ten9an3KA6fKgQJBAP5P6RtrHZW92U5Az7GIFGUWSC500XPS4EaRouaVi4962QeGRZpNRMn51V4ECgNsOyEIQyW6uXNwc/JNXCeOIN8CQQDKXj5DGWxVObrTXC1+gqhmkMXPeW2PxCoYbnoX+WgAowV12J6xZOpk71s6qWxZ8oS2KfNTgM7jzQ/u6lfZpErrAkEA6yqEGgmsevrrBvTh2n5Gtc+sAeGeiid3o2orJGUngA+Ov+mHVY+cS9WOW/kKNW4RI5ObXDlWIozbezXUQPOqKwJBALKAjssBEd3bHkBxfm+pMIHIEbQAl/PIRJloWansBSEKqzZX7SLssRR3TTFhYIG2ufk0570wUU3/A57qdrMziCECQQCaFgDxZf/87y61QSLYhS2O58Leyyad3dl0g6hRICoVYhKsWJPDxNtG0JE17APvTNZo1Zvd2fhwQAsZIiPJwh17";
	// TODO !!!! 注：该公钥为测试账号公钥 开发者必须设置自己的公钥 ,否则会存在安全隐患
	public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJCK0hPTa/RuL/0cPbEEJGrs9UUwBJ6Z8ZI4ygp09geUfAiPePz9Gz1rgMlOmQ1qEWSJ1tzWrHK7wvIawmZNW1ez60CT5jcHhrcFhC4C5fepD8I0yeM6l7leNdz7ueKrDlca3JMmN4vsR59ObkmKzGKOJEPSVi1KciBXm6etSitQIDAQAB";

	/** 支付宝网关 */
	public static final String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";

	/** 授权访问令牌的授权类型 */
	public static final String GRANT_TYPE = "authorization_code";
}