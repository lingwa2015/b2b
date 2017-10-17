require.config({ //第一块，配置
    baseUrl: '/ui/',
    paths: {
        jquery: 'common/js/zepto.min',
        mui: 'common/js/mui.min',
        store: 'common/js/store',
        md5: 'common/js/md5',
        timeago: 'common/js/timeago',
        'timeago.zh-cn': 'common/js/timeago.zh-cn',
        'iscroll-4.25': 'common/js/iscroll-4.25',
        weixinApi: 'common/js/weixinApi',
        alipayApi: 'common/js/alipayApi',
        stacktrace: 'common/js/stacktrace',
        cart: 'common/js/micart-0.0.1',
        avalon: "common/js/avalon/avalon.mi.min",
        //必须修改源码，禁用自带加载器，或直接删提AMD加载器模块
        mmRouter: "common/js/avalon/mmRouter",
        mmHistory: "common/js/avalon/mmHistory",
        'avalon.dialog': "common/js/avalon/dialog/avalon.dialog",
        'avalon.iscroll4': "common/js/avalon/mobile/avalon.iscroll4",
        text: 'common/js/require/text',
        domReady: 'common/js/require/domReady',
        css: 'common/js/require/css.js',

        common: "mobile/js/consumer/common",
        weixin_article: "mobile/js/weixin_article",
        mi_dialog: "mobile/js/consumer/mi_dialog",
        weixin_follow_notice: "mobile/js/consumer/weixin_follow_notice",
        login: "mobile/js/consumer/login",
        signup: "mobile/js/consumer/signup",
        index: "mobile/js/consumer/index",
        feeds: "mobile/js/consumer/feeds",
        account: "mobile/js/consumer/account",
        profile: "mobile/js/consumer/profile",
        item_detail: "mobile/js/consumer/item_detail",
        shop_index: "mobile/js/consumer/shop_index",
        shop_detail: "mobile/js/consumer/shop_detail",
        shop_view: "mobile/js/consumer/shop_view",
        shop_view_simple: "mobile/js/consumer/shop_view_simple",
        confirm_order: "mobile/js/consumer/confirm_order",
        master_order_list: "mobile/js/consumer/master_order_list",
        master_order_detail: "mobile/js/consumer/master_order_detail",
        address_index: "mobile/js/consumer/address_index",
        address_add: "mobile/js/consumer/address_add",
        address_edit: "mobile/js/consumer/address_edit",
        payment_confirm: "mobile/js/consumer/payment_confirm",
        mkt_activities_detail: "mobile/js/consumer/mkt_activities_detail"
    },
    priority: ['text', 'css'],
    urlArgs: 'v=' + window.MiMobile.assets_version,
    shim: {
        mui: {
            exports: "mui"
        },
        jquery: {
            exports: "Zepto"
        },
        timeago: {
            deps: ['jquery']
        },
        'timeago.zh-cn': {
            deps: ['jquery', 'timeago']
        },
        cart: {
            deps: ['store']
        },
        avalon: {
            exports: "avalon"
        }
    }
});