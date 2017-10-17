package com.b2b;

public class Constant {

	public static int VALID_STATUS=1;
	public static int DELETE_STATUS=0;
	public static int THOROUGH_STATUS=2;

	public static int Admin_User=1;
	public static int Common_User=0;

	public static final String USER_KEY = "user";
	public static final String USER_APP_KEY = "appuser";
	public static final String USER_WX_KEY = "wxuser";
	public static final String USER_SHOP_KEY = "shopuser";
	public static final String USER_SHOP_ALI_KEY = "shopaliuser";

	public static final String USER_ADMIN_KEY = "userAdmin";
	public static final String LOGIN_REDIRECT_URL = "appredirectUrl";
	public static final String LOGIN_APP_REDIRECT_URL = "redirectUrl";
	//创建订单来源的标示  app
	public static final String FROM_APP ="app";

	public static String SIZE = "size";
	public static String BUY_SIZE = "buy_size";
	public static String SALE_SIZE = "sale_size";
	
	public static String DEFAULT_TOKEN_MSG_JSP = "unSubmit.jsp" ;
    public static String TOKEN_VALUE ;
    public static String DEFAULT_TOKEN_NAME = "springMVC.token";

}
