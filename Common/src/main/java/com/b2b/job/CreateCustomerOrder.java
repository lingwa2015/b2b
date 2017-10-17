package com.b2b.job;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.StandardOrderItemMapper;
import com.b2b.common.dao.StandardOrderMapper;
import com.b2b.common.domain.CustomerOrder;
import com.b2b.common.domain.CustomerOrderItem;
import com.b2b.common.domain.CustomerWise;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderExample;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.StandardOrderItemExample;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.StandardOrderExample.Criteria;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.CustomerOrderService;
import com.b2b.service.CustomerService;
import com.b2b.service.CustomerWiseService;
import com.b2b.service.ItemService;
import com.b2b.service.UserService;


@Service("createCustomerOrder")
public class CreateCustomerOrder {
	private static final Logger logger = LoggerFactory
			.getLogger(CreateCustomerOrder.class);
	
	@Autowired
	StandardOrderMapper standardOrderMapper;

	@Autowired
	StandardOrderItemMapper standardOrderItemMapper;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	CustomerWiseService customerWiseService;
	
	@Autowired
	CustomerOrderService customerOrderService;
	
	@Autowired
	CustomerService customerService;
	
	public String createOrderInfoByNextWeek()
	{
		return createOrderInfo(0,0,1,0,null);
	}
	
	public String createOrderInfoByStandId(int standardorderId,int userId,CustomerWise customerWise)
	{
		return createOrderInfo(standardorderId,0,0,userId,customerWise);
	}
	
	public String createOrderInfo(int standardorderId,int num,int week,int userId,CustomerWise customerWise){
		StandardOrderExample orderExample = new StandardOrderExample();
		orderExample.setOrderByClause("executed_time asc");
		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andStandardStatusEqualTo(Constant.DELETE_STATUS);
		Date startTime = DateUtil.getWeekDate(week, 1);
		Date endTime = DateUtil.getWeekDate(week, 5);
		if (week > 0) {
			// 开始时间
			criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
			// 结束时间
			criteria.andExecutedTimeLessThanOrEqualTo(endTime);
		}
		criteria.andSnackpackageTypeEqualTo(1);//1随机生成的标准套餐
		if (standardorderId>0) {
			criteria.andStandardorderIdEqualTo(standardorderId);
		}
		//获取标准套餐信息
		List<StandardOrder> orders = standardOrderMapper.selectByExample(orderExample);
		for (StandardOrder order2 : orders) {
			StandardOrderItemExample itemExample = new StandardOrderItemExample();
			itemExample.createCriteria().andStandardorderIdEqualTo(order2.getStandardorderId());
			//获取标准套餐产品信息
			List<StandardOrderItem> itemList = standardOrderItemMapper.selectByExample(itemExample);
			if(num==0){
				num=order2.getWeek();
			}
			if(week==0){
				if(order2.getExecutedTime().getTime()>(new Date()).getTime()){
					week=DateUtil.getWeeks(order2.getExecutedTime())-DateUtil.getWeeks(new Date());
				}
			}
			//获取客户智能选品的属性
			List<CustomerWise> customerWiseList=new ArrayList<CustomerWise>();
			if(customerWise!=null){
				customerWiseList.add(customerWise);
			}else{
				customerWiseList=customerWiseService.getCustomerWiseInfo(num);
			}
			for (CustomerWise wise : customerWiseList) {
				Long totalFeeNew1=0l;
				CustomerOrder customerOrder=new CustomerOrder();
				int customerId=wise.getCustomerId();//客户ID
				Long budget=wise.getBudget();//预算金额
				int startprice=wise.getStartprice()==null?0:wise.getStartprice();//开始金额
				int endprice=wise.getEndprice()==null?0:wise.getEndprice();//截止金额
				int issuperBudget=wise.getIssuperBudget()==null?0:wise.getIssuperBudget();//是否可超预算
				if(userId<=0){
					userId=735;
				}
				int wiseWeek=wise.getWeeks();
				Date executedTime=DateUtil.getWeekDate(week, wiseWeek);
				//设置客户预订单属性
				customerOrder.setStandardorderId(standardorderId); 
				customerOrder.setCustomerId(customerId); 
				customerOrder.setExecutedTime(executedTime);
				
				//查询并设置客户的其他属性
				CustomerUser customerUser = customerService.findById(customerId);
				customerOrder.setBusinessId(customerUser.getBusinessId()); 
				customerOrder.setCustomerAddress(customerUser.getAddress());
				customerOrder.setRemark(customerUser.getCompanyMemo());
				BigDecimal discount=customerUser.getDiscount();
				customerOrder.setDiscount(discount);

				customerOrder.setTotalFee(order2.getTotalFee());
			    customerOrder.setWeek(wiseWeek);
			    customerOrder.setCustomerStatus(0);
			    customerOrder.setStatus(1);
			    customerOrder.setCreatedTime(new Date());
			    customerOrder.setCreatedUserid(userId);
			    customerOrder.setUpdatedTime(new Date());
			    customerOrder.setUpdatedUserid(userId);
			    
			    List<CustomerOrderItem> list1=new ArrayList<CustomerOrderItem>();
			    //循环订单产品项
			    for (StandardOrderItem orderItem : itemList) {
			    	Long itemPrice=orderItem.getItemPrice();
			    	int price=0;
			    	//如果对单品价格有要求，那么单品价格需小于单品截止价格
			    	if(startprice>=0 && endprice>0){
			    		if(itemPrice<=endprice){
			    			price=1;
			    		}
			    	}else{
			    		price=1;
			    	}
			    	if(price==1){
				    	CustomerOrderItem customerOrderItem=new CustomerOrderItem();
					    customerOrderItem.setItemId(orderItem.getItemId()); 
					    customerOrderItem.setItemPrice(itemPrice);
					    totalFeeNew1+=itemPrice;
					    list1.add(customerOrderItem);
			    	}
			    }
			    
//				//产品黑名单（去除）
//				List<Item> itemBlack=itemService.selectBlackItem(customerId);
//				for (Item itemBlacks : itemBlack) {
//					for (CustomerOrderItem orderBlacks : list1) {
//						if(itemBlacks.getId()==orderBlacks.getItemId()){
//							list1.remove(orderBlacks);
//							break;
//						}
//					}
//				}
//				
//				//品种白名单（机选）
//				List<Item> witemhite=itemService.selectWhiteItem(customerId,1);
//				Item varietyItem=new Item();
//				if(witemhite.size()>0){
//					Random rand = new Random();
//					int randNum = rand.nextInt(witemhite.size());
//					varietyItem = witemhite.get(randNum);
//				}
//				
//				//增加产品白名单（不可重复）
//				List<Item> itemWhite=itemService.selectWhiteItem(customerId,2);
//				itemWhite.add(varietyItem);
//				for (Item itemWhites : itemWhite) {
//					int i=1;
//					for (CustomerOrderItem orderBlacks : list1) {
//						if(itemWhites.getId()==orderBlacks.getItemId()){
//							i=0;
//							break;
//						}
//					}
//					if(i==1 && itemWhites.getId()!=null){
//						CustomerOrderItem customerOrderItem=new CustomerOrderItem();
//						Long salePrice=itemWhites.getSalePrice()==null?0:itemWhites.getSalePrice();
//						int stock= itemWhites.getSaleSizeNum()==null?0:itemWhites.getSaleSizeNum();
//				    	customerOrderItem.setItemId(itemWhites.getId());
//				    	customerOrderItem.setItemPrice(salePrice); 
//					}
//				}
				customerOrder.setCustomerOrderList(list1);
				int superBudgetFee = (int)Math.rint(budget/(double)10);
				CustomerOrder resultOrder=this.getCustOrderNum(customerOrder, budget,issuperBudget,superBudgetFee,totalFeeNew1);
				customerOrderService.createCustomerOrder(resultOrder);
			}
		}
		return "客户预订单生成成功！";
	}
	
	//
	public int getNumbyTotalFee(long budget,Long totalFeeNew){
		//预算金额除总费用计算出平均数量【四舍五入】
		//int itemnum = (int)Math.rint(budget/(double)totalFeeNew);
		//预算金额除总费用计算出平均数量【向上取整】
		int itemnum = (int)Math.ceil(budget/(double)totalFeeNew);
		//1、平均数量小于5时取计算数量
		//2、平均数量大于5时，余数小于5时末尾取5，余数大于5时末尾数取10
		if(itemnum>=5){
			int ys=itemnum%10;
			if(ys<=5){
				itemnum=itemnum-ys+5;
			}else if(ys>5){
				itemnum=itemnum-ys+10;
			}
		}
		return itemnum;
	}
	
	public CustomerOrderItem createCustomerOrderItem(Item item,int num){
		CustomerOrderItem itemResult=new CustomerOrderItem();
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
	 * customerOrder:客户订单信息
	 * budget：客户预算
	 * num：商品平均数量
	 * issuperBudget：是否可超预算
	 * superBudgetFee：可超预算的金额
	 * totalFeeNew1：入选商品的零售价格之和
	 * */
	public CustomerOrder getCustOrderNum(CustomerOrder customerOrder,long budget,int issuperBudget,int superBudgetFee,Long totalFeeNew1){
		List<CustomerOrderItem> resultlist=new ArrayList<CustomerOrderItem>();
		List<CustomerOrderItem> list=customerOrder.getCustomerOrderList();
		long totalFee=0;
		Long totalCost=0l;
		int totalNum=0;
		Long totalFeeNew2=0l;
		//第一次获取预算金额平均数量
		int num=getNumbyTotalFee(budget,totalFeeNew1);
		Long totalStockFee=0L;
		for (int i=list.size()-1;i>=0;i--) {
			CustomerOrderItem customerOrderItem=list.get(i);
			if(customerOrderItem!=null){
				Item item = this.itemService.findById(customerOrderItem.getItemId());
				int stock=item.getSaleSizeNum();
				if(stock>1){
					Long salePrice=item.getSalePrice();
					int numNew=0;
					if(stock>1){//处理零售规格不是1的问题
						if(stock>=num){//平均数量小于零售规格时,取1
							numNew=1;
						}else{//平均数量大于零售规格时,平均数量除零售规格的四舍五入取整
							numNew=(int)Math.rint(num/stock);	
						}
					}else{
						numNew=num;
					}
					totalStockFee+=salePrice*numNew;
					totalFee+=item.getSalePrice()*numNew;
			    	totalCost+=item.getSaleCostPrice()*numNew;
			    	totalNum+=numNew;
			    	CustomerOrderItem itemResult=this.createCustomerOrderItem(item, numNew);
			    	resultlist.add(itemResult);  
			    	list.remove(i);
				}else{
					totalFeeNew2+=item.getSalePrice();
				}
			}
		}
		
		// 根据价格排序
		Collections.sort(list,
				new Comparator<CustomerOrderItem>() {
					public int compare(CustomerOrderItem b1,
							CustomerOrderItem b2) {
						Long b2price=b2.getItemPrice()==null?0:b2.getItemPrice();
						Long b1price=b1.getItemPrice()==null?0:b1.getItemPrice();
						return b2price.compareTo(b1price);
					}
				});	
		
		budget=budget-totalStockFee;
		//第二次获取预算金额平均数量（计算零售规格为1的产品数量）
		int num2=getNumbyTotalFee(budget,totalFeeNew2);
		long totalFeeNew=totalFeeNew2*num2;
		long valueFee=totalFeeNew-budget;
		Long itemFee=0l;//累计到本次的金额
		Long itemFee1=0l;//累计到上一次的金额
		for (CustomerOrderItem customerOrderItem : list) {
			Item item = this.itemService.findById(customerOrderItem.getItemId());
			Long fee=item.getSalePrice();
			int newNum=0;
			int step=0;
			if(issuperBudget==1 && valueFee<=superBudgetFee){
				newNum=step=num2;
			}else{
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
//		    			Long fee1=itemFee1-valueFee;
//		    			Long fee2=valueFee-itemFee;
//		    			if(fee1>fee2 && issuperBudget==1){//如果本次预算差额大于上次预算差额，且客户可超预算时，不需减数量
//		    				newNum=num2;
//		    			}
		    		}
		    	}else{
		    		newNum=step=num2;
		    		step=0;
		    	}
			}
			totalFee+=item.getSalePrice()*newNum;
	    	totalCost+=item.getSaleCostPrice()*newNum;
	    	totalNum+=newNum;
	    	CustomerOrderItem itemResult=this.createCustomerOrderItem(item, newNum);
	    	resultlist.add(itemResult); 
	    	itemFee+=fee*step;
		}
		customerOrder.setTotalNum(totalNum); 
		customerOrder.setTotalFee(totalFee);
		customerOrder.setTotalCost(totalCost);
		Collections.sort(resultlist,
		new Comparator<CustomerOrderItem>() {
			public int compare(CustomerOrderItem b1,
					CustomerOrderItem b2) {
				return new Integer(b1.getCategoryId()).compareTo(new Integer(b2.getCategoryId()));
			}
		});
		customerOrder.setCustomerOrderList(resultlist);
		return customerOrder;
	}
}
