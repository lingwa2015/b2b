package com.b2b.enums;



public enum LogDataTypeEnum {

		ITEM_CATEGORY("ITEM_CATEGORY", "商品类目"),
		ITEM("ITEM", "商品"),
		USER("USER", "用户"),
		CUSTOMER("CUSTOMER", "客户"),
		VIPCUSTOMER("CUSTOMER", "微信会员客户"),
		USER_LOGIN("USER_LOGIN", "用户登录"),
		ORDER("ORDER", "订单"),
		PURCHASE("PURCHASE", "采购订单"),
		OUTSTOCK("OUTSTOCK", "采购退货单"),
		PAYMENTAPPLY("PAYMENTAPPLY", "付款申请"),
		PAYMENT("PAYMENT", "付款"),
		STORAGE("STORAGE", "入库单"),
		REFUND("REFUND", "退货单"),
		STOCK("STOCK", "库存"),
		STOCK_CHECK("STOCK_CHECK", "盘库单"),
		UpMonthStock("UpMonthStock", "月底库存快照"),
		DEBIT_NOTE("DEBIT_NOTE", "收款单"),
		Invoice("Invoice", "发票"),
		RECEIPT("RECEIPT", "收发票"),
		Preferential("Preferential", "优惠"),
		Remark("Remark", "备注"),
		Verification("Verification", "核销"),
		Supplier("Supplier","供应商"),
		StandardOrder("StandardOrder","标准套餐"),
		CustomerOrder("CustomerOrder","预订单"),
		WeightCoefficient("WeightCoefficient","权重系数"),
		ItemVariety("ItemVariety","品种"),
		ItemTaste("ItemTaste","口味"),
		CustomeBlackwhite("CustomeBlackwhite","客户黑白名单"),
		BeCustomeBlackwhite("BeCustomeBlackwhite","客户准黑白名单"),
		SnackPackageType("SnackPackageType","零食包类型"),
		WXUserAddress("WXUserAddress","微信客户地址"),
		wxOrder("wxOrder","后台微信订单"),
		WXUserOrder("WXUserOrder","微信客户订单"),
		Role("Role","角色"),
		Privilege("Privilege","权限"),
		RechargeRecord("RechargeRecord","后台会员充值"),
		ShopOrder("ShopOrder","便利店采购订单"),
		ShopItemUpOrDown("ShopItemUpOrDown","便利店商品上下架"),
		ShopItemDown("ShopItemDown","便利店商品下架"),
		ShopSeniorSet("ShopSeniorSet","便利店高级设置"),
		ShopOrderRefund("ShopOrderRefund","便利店订单退款"),
		Shoper("Shoper","便利店用户"),
		Point("Point","积分"),
		Reseau("Reseau","网格"),
		Equipment("Equipment","设备"),
		Machine("Machine","设备"),
		Transfer("Transfer","流转单"),
	    RedPacket("RedPacket","红包活动");
		private String name;
		private String value;

		private LogDataTypeEnum(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}


	}
