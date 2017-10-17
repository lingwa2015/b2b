package com.b2b.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.StandardOrderItemMapper;
import com.b2b.common.dao.StandardOrderMapper;
import com.b2b.common.domain.BaseTranDetail;
import com.b2b.common.domain.CategoryNum;
import com.b2b.common.domain.CustomerOrderItem;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderExample;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.StandardOrderItemExample;
import com.b2b.common.domain.StandardOrderExample.Criteria;
import com.b2b.common.util.DateUtil;
import com.b2b.service.CategoryNumService;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.service.LogService;
import com.b2b.service.StandardOrderService;
import com.b2b.service.UserService;

@Service("autoStandradOrder")
public class AutoStandradOrder {
	private static final Logger logger = LoggerFactory
			.getLogger(AutoStandradOrder.class);

	@Autowired
	StandardOrderService standardOrderService;

	@Autowired
	UserService customerUserService;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	ItemService itemService;

	@Autowired
	LogService logService;
	
	@Autowired
	StandardOrderMapper standardOrderMapper;

	@Autowired
	StandardOrderItemMapper standardOrderItemMapper;
	
	@Autowired
	CreateCustomerOrder createCustomerOrder;
	
	@Autowired
	CategoryNumService categoryNumService;
	
	//获取下周标准套餐
	public String getNextWeekOrder()
	{
		return getStandradOrder(1,1,5,10);
	}
	
	//获取下下周标准套餐
	public String getAfterNextWeekOrder()
	{
		return getStandradOrder(2,1,5,10);
	}
	
	/**
	 * 手动生成零食包
	 * */
	public StandardOrder getSnackPackageOrderbyHand(int standardorderId,Long budgetFee){
		return this.getSnackPackageOrderCommon(standardorderId, budgetFee);
	}
	
	/**
	 * 生成零食包
	 * */
	public String getSnackPackageOrder(int standardorderId,Long budgetFee){
		try{
			StandardOrder snackPackageOrder=this.getSnackPackageOrderCommon(standardorderId, budgetFee);
			return "零食包生成成功！";
		} catch (Exception e) {
			logger.error("自动生成零食包", e);
			return e.getMessage();
		}
	}
	
	/**
	 * 生成零食包
	 * */
	public StandardOrder getSnackPackageOrderCommon(int standardorderId,Long budgetFee){
		StandardOrder snackPackageOrder=new StandardOrder();
		try{
			StandardOrderExample orderExample = new StandardOrderExample();
			Criteria criteria = orderExample.createCriteria();
			criteria.andStatusEqualTo(Constant.VALID_STATUS);
			if (standardorderId>0) {
				criteria.andStandardorderIdEqualTo(standardorderId);
			}
			//获取标准套餐信息
			List<StandardOrder> orders = standardOrderMapper.selectByExample(orderExample);
			if(orders.size()>0){
			StandardOrder order2=orders.get(0);
				if(order2!=null){
					long totalFee=0;
					Long totalCost=0l;
					int totalNum=0;
					Long totalStockFee=0L;//零食规格大于1的产品金额累计
					Long totalStockFee2=0l;//零食规格等于1的产品金额累计
					snackPackageOrder=new StandardOrder();
					snackPackageOrder.setExecutedTime(order2.getExecutedTime()); 
					snackPackageOrder.setWeek(order2.getWeek());
					snackPackageOrder.setCreatedTime(new Date());
					snackPackageOrder.setCreatedUserid(735);
					snackPackageOrder.setUpdatedTime(new Date());
					snackPackageOrder.setUpdatedUserid(735);
					snackPackageOrder.setStandardStatus(Constant.DELETE_STATUS);
					snackPackageOrder.setStatus(Constant.VALID_STATUS);
					snackPackageOrder.setSnackpackageStatus(Constant.DELETE_STATUS);
					snackPackageOrder.setSnackpackageType(3);//标准套餐 
					snackPackageOrder.setParentid(standardorderId);
					StandardOrderItemExample itemExample = new StandardOrderItemExample();
					itemExample.createCriteria().andStandardorderIdEqualTo(order2.getStandardorderId());
					//获取标准套餐产品信息
					List<StandardOrderItem> itemList = standardOrderItemMapper.selectByExample(itemExample);
					List<StandardOrderItem> resultlist=new ArrayList<StandardOrderItem>();
					//第一次获取预算金额平均数量
					int num=createCustomerOrder.getNumbyTotalFee(budgetFee,order2.getTotalFee());
					for (int i=itemList.size()-1;i>=0;i--) {
						StandardOrderItem standardOrderItem=itemList.get(i);
						if(standardOrderItem!=null){
							Item item = this.itemService.findById(standardOrderItem.getItemId());
							int stock=item.getSaleSizeNum();
							Long salePrice=item.getSalePrice();
							int numNew=0;
							if(stock>1){
								if(stock>=num){//平均数量小于零售规格时,取1
									numNew=1;
								}else{//平均数量大于零售规格时,平均数量除零售规格的四舍五入取整
									numNew=(int)Math.rint(num/stock);	
								}
								totalStockFee+=salePrice*numNew;
								totalFee+=salePrice*numNew;
						    	totalCost+=item.getSaleCostPrice()*numNew;
						    	totalNum+=numNew;
						    	StandardOrderItem itemResult=this.createStandardOrderItem(item, numNew);
						    	resultlist.add(itemResult); 
						    	itemList.remove(i);
							}else{
								totalStockFee2+=salePrice;
							}
						}
					}
					budgetFee=budgetFee-totalStockFee;
					//第二次获取预算金额平均数量（计算零售规格为1的产品数量）
					int num2=createCustomerOrder.getNumbyTotalFee(budgetFee,totalStockFee2);
					// 根据价格排序
					Collections.sort(itemList,
							new Comparator<StandardOrderItem>() {
								public int compare(StandardOrderItem b1,
										StandardOrderItem b2) {
									Long b2price=b2.getItemPrice()==null?0:b2.getItemPrice();
									Long b1price=b1.getItemPrice()==null?0:b1.getItemPrice();
									return b2price.compareTo(b1price);
								}
							});	
					long totalFeeNew=totalStockFee2*num2;
					long valueFee=totalFeeNew-budgetFee;
					Long itemFee=0l;//累计到本次的金额
					Long itemFee1=0l;//累计到上一次的金额
					for (StandardOrderItem standardOrderItem : itemList) {
						Item item = this.itemService.findById(standardOrderItem.getItemId());
						Long fee=item.getSalePrice();
						int newNum=0;
						int step=0;
						if(itemFee<valueFee){//临界值比较问题
				    		newNum=num2;
				    		if(newNum>5){//大于5时，整5减
						    	//当累计商品*5的金额大于总金额与预算费用差额时不再递减商品数量
				    			step=5;//步长
							}else{
								step=1;
							}
				    		newNum=newNum-step;
				    		itemFee1+=fee*step;
				    		if(itemFee1>valueFee){//如果加上本次商品金额后大于预算差额，则判断两次金额与预算差额的差值
				    			newNum=step=num2;
				    		}
				    	}else{
				    		newNum=step=num2;
				    		step=0;
				    	}	
						totalFee+=item.getSalePrice()*newNum;
				    	totalCost+=item.getSaleCostPrice()*newNum;
				    	totalNum+=newNum;
				    	StandardOrderItem itemResult=this.createStandardOrderItem(item, newNum);
				    	resultlist.add(itemResult); 
				    	itemFee+=fee*step;
					}
					snackPackageOrder.setTotalNum(totalNum); 
					snackPackageOrder.setTotalFee(totalFee);
					snackPackageOrder.setTotalCost(totalCost);
					Collections.sort(resultlist,
					new Comparator<StandardOrderItem>() {
						public int compare(StandardOrderItem b1,
								StandardOrderItem b2) {
							return new Integer(b1.getCategoryId()).compareTo(new Integer(b2.getCategoryId()));
						}
					});
					snackPackageOrder.setStandardOrderList(resultlist);
//					standardOrderService.createStandardOrder(snackPackageOrder);
				}
			}
		} catch (Exception e) {
			logger.error("自动生成零食包", e);
		}
		return snackPackageOrder;
	}
	
	public StandardOrderItem createStandardOrderItem(Item item,int num){
		StandardOrderItem itemResult=new StandardOrderItem();
		itemResult.setItemId(item.getId());
		itemResult.setItemName(item.getItemName());
		itemResult.setNum(num);
		itemResult.setFee(item.getSalePrice()*num); //零售价格
		itemResult.setStockNum(item.getSaleSizeNum()*num); //零售数量
		itemResult.setItemPrice(item.getSalePrice()); 
		itemResult.setItemSize(item.getSaleSize()); //零售规格
		itemResult.setItemCostPrice(item.getSaleCostPrice());//零售成本	
		itemResult.setItemSizeType("SALE_SIZE");//零售规格
		itemResult.setCategoryId(item.getCategoryId());
		return itemResult;
	}
	
	/**
	 * 标准套餐
     * week：第几周
     * startDay：周几开始
     * endDay:周几结束
     * itemNum:一天多少个品
     * */
	public String getStandradOrder(int week,int startDay,int endDay,int itemNum) {
		try {
			Date stratTime = DateUtil.getWeekDate(week, startDay);
			Date endTime = DateUtil.getWeekDate(week, endDay);
			int standardOrderCount = standardOrderService.selectStandardOrderCount(stratTime, endTime,1);
			if (standardOrderCount <= 0) {
				List<CategoryNum> itemCategoryNum=categoryNumService.selectAll();
//				List<ItemCategoryNum> itemCategoryNum=new ArrayList<ItemCategoryNum>();
//				itemCategoryNum.add(new ItemCategoryNum(24,4));//饼干糕点数量
//				itemCategoryNum.add(new ItemCategoryNum(25,1));//蜜饯果干数量
//				itemCategoryNum.add(new ItemCategoryNum(26,3));//熟食速食数量
//				itemCategoryNum.add(new ItemCategoryNum(27,1));//坚果炒货数量
//				itemCategoryNum.add(new ItemCategoryNum(28,1));//糖果巧克力数量
				int sum = 0;
				int random = 0;
				int weeks = 0;
				Date executedTime = null;
				ItemWeight kw = null;
				List<StandardOrderItem> orderItemList = new ArrayList<StandardOrderItem>();
				StandardOrder standardOrder = new StandardOrder();
				StandardOrderItem standardOrderItem = new StandardOrderItem();
				List<Integer> itemNotRepetitionID = new ArrayList<Integer>();
				for (int k = 0; k < endDay; k++) {
					// 生成下周套餐
					weeks = k + 1;
					executedTime = DateUtil.getWeekDate(week, weeks);

					standardOrder = new StandardOrder();
					// 设置标准套餐主表默认值
					standardOrder.setCreatedTime(new Date());
					standardOrder.setCreatedUserid(735);
					standardOrder.setUpdatedTime(new Date());
					standardOrder.setUpdatedUserid(735);
					standardOrder.setStandardStatus(Constant.DELETE_STATUS);
					standardOrder.setStatus(Constant.VALID_STATUS);
					standardOrder.setExecutedTime(executedTime);
					standardOrder.setWeek(weeks);
					standardOrder.setSnackpackageType(1);//套餐类型1、标准套餐 2、零食包
					standardOrder.setSnackpackageStatus(Constant.DELETE_STATUS);
					orderItemList = new ArrayList<StandardOrderItem>();
					Long orderTotalFee = 0l;
					for (CategoryNum itemCat : itemCategoryNum) {
						int catId=itemCat.getCategoryid();
						int catNum=itemCat.getNum();
						// 获取待选商品信息
						List<Item> listItem = itemService.selectAutoItemByCategoryId(week-1,catId);
						for (int cat = 0; cat < catNum; ) {
							int foreachNum=0;
							// 生成待选商品id，权重集合
							List<ItemWeight> itemWeight = new ArrayList<ItemWeight>();
							ItemWeight n = new ItemWeight();
							for (int nums = 0; nums < listItem.size(); nums++) {
								Item item = listItem.get(nums);
								n = new ItemWeight(item.getId(), item.getWeight().doubleValue());
								itemWeight.add(n);
							}
							standardOrderItem = new StandardOrderItem();
							sum = getSum(itemWeight); // 获取权重之和
							random = getRandom(sum); // 获取随机权重数
							kw = getKW(itemWeight, random); // 获取随机权重对应的对象
							int itemidRandom = kw.itemid;
	
							for (int m = 0; m < itemNotRepetitionID.size(); m++) {
								int itemid=itemNotRepetitionID.get(m);
								if (itemid == itemidRandom) {
//									cat=cat-1;
									foreachNum=1;
									break;
								}
							}
							if(foreachNum==0){
								cat++;
								itemNotRepetitionID.add(itemidRandom);
								for (int m = 0; m < listItem.size(); m++) {
									Item itemInfo = listItem.get(m);
									if (itemInfo.getId() == itemidRandom) {
										standardOrderItem.setItemId(itemidRandom);
										standardOrderItem.setItemName(itemInfo.getItemName());
										standardOrderItem.setNum(1);
										standardOrderItem.setFee(itemInfo.getPrice() == null ? 0 : itemInfo.getPrice() * 1);// 获取最小规格价格
										standardOrderItem.setItemPrice(itemInfo.getPrice() == null ? 0 : itemInfo.getPrice());
										standardOrderItem.setItemCostPrice(itemInfo.getCostPrice() == null ? 0: itemInfo.getCostPrice());
										standardOrderItem.setItemSize(itemInfo.getSize());
										standardOrderItem.setItemSizeType("SIZE");
										standardOrderItem.setCategoryId(itemInfo.getCategoryId());// 获取类目信息方便排序
										orderTotalFee += standardOrderItem.getFee();
										break;
									}
								}
		
								orderItemList.add(standardOrderItem);
								if (itemWeight.size() <= 0) {
									break;
								}
							}
						}	
					}
					standardOrder.setTotalFee(orderTotalFee);
					// 根据Collections.sort重载方法来实现
					Collections.sort(orderItemList,
							new Comparator<StandardOrderItem>() {
								public int compare(StandardOrderItem b1,
										StandardOrderItem b2) {
									return new Integer(b1.getCategoryId())
											.compareTo(new Integer(b2
													.getCategoryId()));
								}
							});
					standardOrder.setStandardOrderList(orderItemList);
					standardOrderService.createStandardOrder(standardOrder);
//					if (itemWeight.size() <= 0) {
//						break;
//					}
				}
				return "标准套餐生成成功！";
			} else {
				return "失败，标准套餐已存在！";
			}
		} catch (Exception e) {
			logger.error("自动生成标准套餐", e);
			return e.getMessage();
		}
	}
	
//    /*
//     * week：第几周
//     * num：一周配多少天
//     * itemNum:一天多少个品
//     * */
//	public String getStandradOrder(int week,int num,int itemNum) {
//		try {
//			//int num = 5;
//			Date stratTime = DateUtil.getWeekDate(week, 1);
//			Date endTime = DateUtil.getWeekDate(week, 5);
//			int standardOrderCount = standardOrderService
//					.selectStandardOrderCount(stratTime, endTime);
//			if (standardOrderCount <= 0) {
//				// 暂时取消自动生成是10还是五天的判断
//				// int num = 10;// 默认生成10天的标准套餐
//				// int standardOrderCount = standardOrderService.selectStandardOrderCount();
//				// if (standardOrderCount > 0) {
//				// num = 5;// 如果标准订单已经生成过，那么生成5天即可
//				// }
//				List<ItemCategoryNum> itemCategoryNum=new ArrayList<ItemCategoryNum>();
//				itemCategoryNum.add(new ItemCategoryNum(24,4));//饼干糕点数量
//				itemCategoryNum.add(new ItemCategoryNum(25,1));//蜜饯果干数量
//				itemCategoryNum.add(new ItemCategoryNum(26,3));//熟食速食数量
//				itemCategoryNum.add(new ItemCategoryNum(27,1));//坚果炒货数量
//				itemCategoryNum.add(new ItemCategoryNum(28,1));//糖果巧克力数量
//				
//				// 获取待选商品信息
//				List<Item> listItem = itemService.selectAutoItem(week-1);
//				// 生成待选商品id，权重集合
//				List<ItemWeight> itemWeight = new ArrayList<ItemWeight>();
//				ItemWeight n = new ItemWeight();
//				for (int nums = 0; nums < listItem.size(); nums++) {
//					Item item = listItem.get(nums);
////					if(item.getId()==null || item.getWeight()==null){
////						logger.error(item.getId()+"=="+item.getWeight(), new Exception());
////					}
//					n = new ItemWeight(item.getId(), item.getWeight().doubleValue());
//					itemWeight.add(n);
//				}
//
//				int sum = 0;
//				int random = 0;
//				int weeks = 0;
//				Date executedTime = null;
//				ItemWeight kw = null;
//				List<StandardOrderItem> orderItemList = new ArrayList<StandardOrderItem>();
//				StandardOrder standardOrder = new StandardOrder();
//				StandardOrderItem standardOrderItem = new StandardOrderItem();
//				for (int k = 0; k < num; k++) {
//					// if (k >= 5) {// 设置星期
//					// week = (k + 1) - 5;
//					// executedTime = getWeekDate(2, week);
//					// } else {
//					// if (num > 5) {
//					// week = k + 1;
//					// executedTime = getWeekDate(1, week);
//					// } else {
//					// week = k + 1;
//					// executedTime = getWeekDate(2, week);
//					// }
//					// }
//
//					// 生成下周套餐
//					weeks = k + 1;
//					executedTime = DateUtil.getWeekDate(week, weeks);
//
//					standardOrder = new StandardOrder();
//					// 设置标准套餐主表默认值
//					standardOrder.setCreatedTime(new Date());
//					standardOrder.setCreatedUserid(735);
//					standardOrder.setUpdatedTime(new Date());
//					standardOrder.setUpdatedUserid(735);
//					standardOrder.setStandardStatus(Constant.DELETE_STATUS);
//					standardOrder.setStatus(Constant.VALID_STATUS);
//					standardOrder.setExecutedTime(executedTime);
//					standardOrder.setWeek(weeks);
//					standardOrder.setSnackpackageType(1);//套餐类型1、标准套餐 2、零食包
//					standardOrder.setSnackpackageStatus(Constant.DELETE_STATUS);
//					orderItemList = new ArrayList<StandardOrderItem>();
//					Long orderTotalFee = 0l;
//					for (int i = 0; i < itemNum; i++) {
//						standardOrderItem = new StandardOrderItem();
//						sum = getSum(itemWeight); // 获取权重之和
//						random = getRandom(sum); // 获取随机权重数
//						kw = getKW(itemWeight, random); // 获取随机权重对应的对象
//						int itemidRandom = kw.itemid;
//
//						for (int m = 0; m < itemWeight.size(); m++) {
//							if (itemWeight.get(m).itemid == kw.itemid) {
//								itemWeight.remove(m);
//							}
//						}
//
//						for (int m = 0; m < listItem.size(); m++) {
//							Item itemInfo = listItem.get(m);
//							if (itemInfo.getId() == itemidRandom) {
//								standardOrderItem.setItemId(itemidRandom);
//								standardOrderItem.setItemName(itemInfo.getItemName());
//								standardOrderItem.setNum(1);
//								standardOrderItem.setFee(itemInfo.getPrice() == null ? 0 : itemInfo.getPrice() * 1);// 获取最小规格价格
//								standardOrderItem.setItemPrice(itemInfo.getPrice() == null ? 0 : itemInfo.getPrice());
//								standardOrderItem.setItemCostPrice(itemInfo.getCostPrice() == null ? 0: itemInfo.getCostPrice());
//								standardOrderItem.setItemSize(itemInfo.getSize());
//								standardOrderItem.setItemSizeType("SIZE");
//								standardOrderItem.setCategoryId(itemInfo.getCategoryId());// 获取类目信息方便排序
//								orderTotalFee += standardOrderItem.getFee();
//								break;
//							}
//						}
//
//						orderItemList.add(standardOrderItem);
//						if (itemWeight.size() <= 0) {
//							break;
//						}
//					}
//					standardOrder.setTotalFee(orderTotalFee);
//					// 根据Collections.sort重载方法来实现
//					Collections.sort(orderItemList,
//							new Comparator<StandardOrderItem>() {
//								public int compare(StandardOrderItem b1,
//										StandardOrderItem b2) {
//									return new Integer(b1.getCategoryId())
//											.compareTo(new Integer(b2
//													.getCategoryId()));
//								}
//							});
//					standardOrder.setStandardOrderList(orderItemList);
//					standardOrderService.createStandardOrder(standardOrder);
//					if (itemWeight.size() <= 0) {
//						break;
//					}
//				}
//				return "标准套餐生成成功！";
//			} else {
//				return "失败，标准套餐已存在！";
//			}
//		} catch (Exception e) {
//			logger.error("自动生成标准套餐", e);
//			return e.getMessage();
//		}
//	}

	// 根据权重随机值获取权重商品
	public static ItemWeight getKW(List<ItemWeight> nodes, int rd) {
		ItemWeight ret = null;
		int curWt = 0;
		for (ItemWeight n : nodes) {
			curWt += n.weight;
			if (curWt >= rd) {
				ret = n;
				break;
			}
		}
		return ret;
	}

	public static int getSum(List<ItemWeight> nodes) {
		int sum = 0;
		for (ItemWeight n : nodes)
			sum += n.weight;
		return sum;
	}

	// 权重合计范围内取一个随机数
	public static int getRandom(int seed) {
		int random=(int) Math.round(Math.random() * seed);
//		System.out.println(random);
		return random;
	}
	
	//类目数量
	class ItemCategoryNum{
		int id=0;
		int num=0;
		public ItemCategoryNum(int id, int num) {
			this.id = id;
			this.num = num;
		}
	}

	//商品权重
	class ItemWeight implements Comparator {
		int itemid = 0;
		double weight = 0;

		public ItemWeight() {
		}

		public ItemWeight(int itemid, double d) {
			this.itemid = itemid;
			this.weight = d;
		}

		public String toString() {
			StringBuilder sbBuilder = new StringBuilder();
			sbBuilder.append(" weight=").append(weight);
			sbBuilder.append(" itemid=").append(itemid);
			return sbBuilder.toString();
		}

		public int compare(Object o1, Object o2) {
			ItemWeight n1 = (ItemWeight) o1;
			ItemWeight n2 = (ItemWeight) o2;
			if (n1.weight > n2.weight)
				return 1;
			else
				return 0;
		}
	}
}
