package com.sxc.natasha.common;

/**
 * Created by longpo on 15/3/13.
 */
public class SXC_API {

    private static final String BASE_URL = "http://112.124.116.88:9527";
    public static final String GET_ALL_ITEM = BASE_URL + "/item/getAllItem.json";
    public static final String GET_USER_ORDER = BASE_URL + "/order/getOrder.json?mobile=";
    public static final String GET_ORDER_DETAIL = BASE_URL + "/order/getOrderByOrderNo.json?orderNo=";
    public static final String POST_USER_INFO =   BASE_URL + "/user/updateUser.json";
    public static final String POST_CHECKCODE =   BASE_URL + "/checkcode/getCheckCode.json";
    public static final String GET_ALL_STORE_HOUSE = BASE_URL + "/storehouse/getAllStoreHouses.json";
    public static final String GET_STORE_HOUSE_BY_PHONE = BASE_URL + "/storehouse/getStoreHouseByUserPhone.json?phoneNumber=";
    public static final String GET_CTREATE_PICKUP_INFO = BASE_URL + "/storehouse/createPickupInfo.json?pickerName={0}&pickerPhone={1}&storehouseId={2}&userId={3}";
    public static final String GET_USER_ACCOUNT = BASE_URL + "/account/getAccount.json";
    public static final String PAY_ORDER = BASE_URL + "/order/orderPay.json";
    public static final String POST_VALIDATECODE =   BASE_URL + "/checkcode/validateCheckCode.json";
    public static final String GET_ITEM_BY_IDS =   BASE_URL + "/item/getItemById.json?ids=";
    public static final String POST_RESETPWD =   BASE_URL + "/user/resetPwd.json";
    public static final String POST_DO_CREATE_ORDER = BASE_URL + "/order/createOrder.json";
    public static final String GET_MODIFY_DEFAULT_PICKHOUSE = BASE_URL + "/storehouse/modifyDefaultPickHouse.json?userId={0}&pickHouseId={1}";
    public static final String GET_LOGIN = BASE_URL + "/user/validateUser.json?mobilePhone=@mp&password=@ps";
    public static final String GET_ACCOUNT_DETAIL= BASE_URL + "/accountDetail/getAccountDetail.json?userId=";
    public static final String GET_DISCOUNTED_FEE= BASE_URL + "/privilege/getDiscountedFee.json?num=@num&fee=@fee";


    public static final String BAIDU_OPEN_API_LOCATION = "http://api.map.baidu.com/location/ip?";
    public static final String BAIDU_OPEN_API_AK="o749xKxYxMuGAEvXwFxpuFfe";

    public static final String GET_LAST_VERSION_CODE=BASE_URL+"/checkForUpdate/getLastVersionCode.json";
    public static final String DOWNLOAD_APK_URL=BASE_URL+"/img/app-release.apk";
    public static final String DOWNLOAD_SAVE_PATH = "/sdcard/updateSxcApp/";
    public static final String DOWNLOAD_SAVE_NAME = DOWNLOAD_SAVE_PATH + "sxcAppUpdate.apk";

    public static final String BANNER_UPDATE_URL= "http://112.124.116.88/banner/getAllBanner.do";
    public static final String ITEM_DETAIL_BANNER_URL = "http://112.124.116.88/upload/getAllItemBanner.do?mainId=";
    public static final String ITEM_DETAIL_INTRODUCTION_URL = "http://112.124.116.88/item/itemDetailPageShow.htm?id=";
    public static final String ITEM_DETAIL_ATTRIBUTE_URL = "http://112.124.116.88/item/itemSpec.htm?id=";

    public static final String POST_VALIDATE_OLD_PHONE =   BASE_URL + "/user/validateOldPhone.json";
    public static final String POST_ADD_ERROR_REPORT =   BASE_URL + "/errorReport/addReport.json";

    public static final String POST_VALIDATE_NEW_PHONE =   BASE_URL + "/user/validateNewPhone.json";
    public static final String GET_BUYER_PICK_HOUSE =   BASE_URL + "/storehouse/getBuyerPickHouseById.json?id=";
    public static final String GET_STORE_HOUSE =   BASE_URL + "/storehouse/getStoreHouseById.json?id=";
    public static final String MODIFY_BUYER_PICK_HOUSE = BASE_URL + "/storehouse/editBuyerPickHouseById.json";

}
