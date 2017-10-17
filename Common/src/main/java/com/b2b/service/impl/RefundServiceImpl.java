package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.Constant;
import com.b2b.common.dao.RefundItemMapper;
import com.b2b.common.dao.RefundMapper;
import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Refund;
import com.b2b.common.domain.RefundExample;
import com.b2b.common.domain.ShopBlackList;
import com.b2b.common.domain.Stock;
import com.b2b.common.domain.WXConsumRecords;
import com.b2b.common.domain.RefundExample.Criteria;
import com.b2b.common.domain.RefundItem;
import com.b2b.common.domain.RefundItemExample;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.util.EncryptHelper;
import com.b2b.dto.RefundDto;
import com.b2b.page.Page;
import com.b2b.service.AccountLockService;
import com.b2b.service.CustomerService;
import com.b2b.service.ItemService;
import com.b2b.service.RefundService;
import com.b2b.service.ShopBlackListService;
import com.b2b.service.StockService;
import com.b2b.service.UserService;
import com.b2b.service.WXAccountService;
import com.b2b.service.WXConsumRecordsService;
import com.google.common.collect.Maps;

@Service
public class RefundServiceImpl implements RefundService {

    private static final Logger logger = LoggerFactory.getLogger(RefundServiceImpl.class);

    @Autowired
    RefundMapper refundMapper;

    @Autowired
    RefundItemMapper refundItemMapper;

    @Autowired
    StockService stockService;

	@Autowired
	CustomerService customerService;

	@Autowired
	ItemService itemService;
	
	@Autowired
	AccountLockService accountLockService; 
	
	@Autowired
	WXAccountService wxAccountService;
	
	@Autowired
	WXConsumRecordsService wxConsumRecordsService;
	
	@Autowired
	ShopBlackListService shopBlackListService;
	
	@Autowired
	UserService userService;
	
	@Override
	public List<Refund> findByCondition(
			String userName, Date startTime, Date endTime, String param,
			int reseauId,Integer cityId,String itemName) {
		List<Refund> refunds = this.refundMapper.findByCondition(userName,startTime,endTime,param,reseauId,cityId,itemName);
        for (Refund refund2 : refunds) {
        	//public int findLock(AccountLock dto){
            List<RefundItem> itemList = this.findRefundItem(refund2.getId());
            CustomerUser refundUser = customerService.findById(refund2.getUserId());
            if (refundUser != null) {
            	refund2.setUserName(refundUser.getUserName());
            	refund2.setMobilePhone(refundUser.getMobilePhone());
            }
            if(null!=refund2.getLastModUser()){
            	PersonUser personUser = this.userService.findById(refund2.getLastModUser());
            	if(null!=personUser){
            		refund2.setOperating(personUser.getUserName());
            	}
            }
            AccountLock accountLock=new AccountLock();
            Date exce=refund2.getExecutedTime();
            Calendar calendar = Calendar.getInstance();
			calendar.setTime(exce);
			int year = calendar.get(Calendar.YEAR); 
			int month = calendar.get(Calendar.MONTH)+1; 
            accountLock.setYears(year+"");
            accountLock.setMonths(month+"");
            accountLock.setCityId(cityId);
            int lock=accountLockService.findLockByCityId(accountLock);
            refund2.setIsLock(lock);
            refund2.setRefundItemList(itemList);
        }
		return refunds;
	}

    @Override
    public Pair<Refund, List<RefundItem>> findById(int id) {
        Refund refund = refundMapper.selectByPrimaryKey(id);
        CustomerUser customerUser = this.customerService.findById(refund.getUserId());
        refund.setUserName(customerUser.getUserName());
        List<RefundItem> itemList = this.refundItemMapper.findRefundItemWithPosition(refund.getId());

        return Pair.of(refund, itemList);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    @Override
    public void createRefund(Refund refund) {
        refund.setState(Constant.VALID_STATUS);
        refund.setCreatedTime(new Date());
        refund.setModifiedTime(new Date());
        this.refundMapper.insert(refund);

        /*
        if(refund.getRefundItemList().size()>0){
        	throw new RuntimeException("测试事务");
        }
        */

        List<RefundItem> itemList = refund.getRefundItemList();
    	for(RefundItem refundItem : itemList){
    		refundItem.setRefundId(refund.getId());
    		this.refundItemMapper.insert(refundItem);
    		
    		Item item = itemService.findById(refundItem.getItemId());
    		int stockNum = item.calNum(refundItem.getNum(), refundItem.getSize());
    		stockService.updateForAdd(refundItem.getItemId(), stockNum);
    		
    		//throw new RuntimeException("测试事务");
    		if(1==refundItem.getReason() && stockNum>=3){
    			ShopBlackList shopBlackList = this.shopBlackListService.findByShopIdAndItemId(refund.getUserId(),refundItem.getItemId());
    			if(null==shopBlackList){
    				ShopBlackList list = new ShopBlackList();
    				list.setItemId(refundItem.getItemId());
    				list.setShopId(refund.getUserId());
    				list.setState(1);
    				list.setType(1);
    				this.shopBlackListService.insert(list);
    			}
    		}
    	}
        	
        
    }

    @Override
    public void updateRefund(Refund refund) {
        refund.setState(Constant.VALID_STATUS);
        refund.setModifiedTime(new Date());
        this.refundMapper.updateByPrimaryKeySelective(refund);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void delete(int id) {
       Pair<Refund, List<RefundItem>> pair = this.findById(id);
       Refund refund = pair.getLeft();
       refund.setState(Constant.DELETE_STATUS);
       refund.setModifiedTime(new Date());
       this.refundMapper.updateByPrimaryKeySelective(refund);

       //更新库存
       List<RefundItem> itemList = pair.getRight();
       if(CollectionUtils.isNotEmpty(itemList)){
           for(RefundItem item : itemList){
        	   Item items = itemService.findById(item.getItemId());
               int stockNum = items.calNum(item.getNum(), item.getSize());
               stockService.updateForAdd(item.getItemId(), -stockNum);
           }
       }
    }

    private List<RefundItem> findRefundItem(int refundId){
        RefundItemExample itemExample = new RefundItemExample();
        itemExample.createCriteria().andRefundIdEqualTo(refundId);
        List<RefundItem> itemList = refundItemMapper.selectByExample(itemExample);

        if(CollectionUtils.isEmpty(itemList)){
            logger.warn("无法找到退货单商品:"+refundId);
        }
        return itemList;
    }

	@Override
	public List<Refund> find(int userId,Date startTime, Date endTime) {

        RefundExample refundExample = new RefundExample();

        Criteria criteria = refundExample.createCriteria();
        refundExample.setOrderByClause("executed_time asc ");
        criteria.andStateEqualTo(Constant.VALID_STATUS);
        criteria.andUserIdEqualTo(userId);

        // 开始时间
        if (startTime != null) {
            criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
        }

        // 结束时间
        if (endTime != null) {
            criteria.andExecutedTimeLessThanOrEqualTo(endTime);
        }

        //Map<Integer,Item> itemMap = Maps.newHashMap();

        List<Refund> list = refundMapper.selectByExample(refundExample);
        /*
        if(CollectionUtils.isNotEmpty(list)){
        	for(Refund refund : list){
        		List<RefundItem> itemList = this.findRefundItem(refund.getId());
        		for(RefundItem refundItem : itemList){
        			Item item = itemMap.get(refundItem.getItemId());
        			if(item==null){
        				item = itemService.findById(refundItem.getItemId());
        				itemMap.put(refundItem.getItemId(), item);
        			}

        			long amount = item.getPrice()*refundItem.getNum();
        			refund.setAmount(amount+refund.getAmount());
        		}
        	}
        }
        */

		return list;
	}

	@Override
	public void updateRefundTotalFee() {
		RefundExample refundExample = new RefundExample();
		Criteria criteria = refundExample.createCriteria();
	    criteria.andStateEqualTo(Constant.VALID_STATUS);

		List<Refund> refundList = refundMapper.selectByExample(refundExample);
		if(CollectionUtils.isNotEmpty(refundList)){
			for(Refund refund : refundList){
				List<RefundItem> itemList = this.findRefundItem(refund.getId());
				if(CollectionUtils.isNotEmpty(itemList)){
					long totalFee = 0;

					for(RefundItem item : itemList){
						Long fee = 0l;
						if(fee==null||fee==0){
                           Item i = itemService.findById(item.getItemId());
                           if(i!=null){
                        	   fee = i.getPrice()*item.getNum();
                        	   item.setTotalFee(fee);
                        	   refundItemMapper.updateByPrimaryKey(item);
                           }
						}
						totalFee += fee;
					}

					logger.warn("refundId:"+refund.getId()+",oldTotalFee:"+refund.getTotalFee()+",totalFee:"+totalFee);
					if(totalFee!=refund.getTotalFee()){
						logger.warn("更新退货单总金额,refundId:"+refund.getId()+",oldTotalFee:"+refund.getTotalFee()+",totalFee:"+totalFee);
						refund.setTotalFee(totalFee);

						this.updateRefund(refund);
					}
				}
			}
		}

	}

//	@Override
//	public List<Refund> findTranSumRefunds(Date startTime, Date endTime) {
//		  RefundExample refundExample = new RefundExample();
//
//	        Criteria criteria = refundExample.createCriteria();
//	        refundExample.setOrderByClause("executed_time asc ");
//	        criteria.andStateEqualTo(Constant.VALID_STATUS);
//
//	        // 开始时间
//	        if (startTime != null) {
//	            criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
//	        }
//
//	        // 结束时间
//	        if (endTime != null) {
//	            criteria.andExecutedTimeLessThanOrEqualTo(endTime);
//	        }
//
//	        //Map<Integer,Item> itemMap = Maps.newHashMap();
//
//	        List<Refund> list = refundMapper.selectByExample(refundExample);
//	        /*
//	        if(CollectionUtils.isNotEmpty(list)){
//	        	for(Refund refund : list){
//	        		List<RefundItem> itemList = this.findRefundItem(refund.getId());
//	        		for(RefundItem refundItem : itemList){
//	        			Item item = itemMap.get(refundItem.getItemId());
//	        			if(item==null){
//	        				item = itemService.findById(refundItem.getItemId());
//	        				itemMap.put(refundItem.getItemId(), item);
//	        			}
//
//	        			long amount = item.getPrice()*refundItem.getNum();
//	        			refund.setAmount(amount+refund.getAmount());
//	        		}
//	        	}
//	        }
//	        */
//
//			return list;
//	}

	@Override
	public void createRefundAndMoney(Refund refund, Integer id) {
		 refund.setState(Constant.VALID_STATUS);
	        refund.setCreatedTime(new Date());
	        refund.setModifiedTime(new Date());
	        this.refundMapper.insert(refund);

	        /*
	        if(refund.getRefundItemList().size()>0){
	        	throw new RuntimeException("测试事务");
	        }
	        */

	        List<RefundItem> itemList = refund.getRefundItemList();
	        for(RefundItem refundItem : itemList){
	        	refundItem.setRefundId(refund.getId());
	            this.refundItemMapper.insert(refundItem);

	            Item item = itemService.findById(refundItem.getItemId());
	            int stockNum = item.calNum(refundItem.getNum(), refundItem.getSize());
	            stockService.updateForAdd(refundItem.getItemId(), stockNum);

	            //throw new RuntimeException("测试事务");
	        }
	        this.wxAccountService.updateForAddMoney(refund.getUserId(), refund.getTotalFee());
			insertWXConsumRecords(EncryptHelper.md5(refund.getId().toString()), refund.getUserId(), id, refund.getTotalFee());
		
	}
	
	public void insertWXConsumRecords(String orderNo,Integer customerId,Integer userId,Long money){
		WXConsumRecords consumRecords = new WXConsumRecords();
		consumRecords.setCustomerId(customerId);
		consumRecords.setMoney(money);
		consumRecords.setOrderNo(orderNo);
		consumRecords.setUserId(userId);
		consumRecords.setCreatedTime(new Date());
		this.wxConsumRecordsService.insert(consumRecords);
	}

	@Override
	public void deleteAndBackMoney(int id, Integer userId) {
		 Pair<Refund, List<RefundItem>> pair = this.findById(id);
	       Refund refund = pair.getLeft();
	       refund.setState(Constant.DELETE_STATUS);
	       refund.setModifiedTime(new Date());
	       this.refundMapper.updateByPrimaryKeySelective(refund);

	       //更新库存
	       List<RefundItem> itemList = pair.getRight();
	       if(CollectionUtils.isNotEmpty(itemList)){
	           for(RefundItem item : itemList){
	        	   Item items = itemService.findById(item.getItemId());
	               int stockNum = items.calNum(item.getNum(), item.getSize());
	               stockService.updateForAdd(item.getItemId(), -stockNum);
	           }
	       }
	       this.wxAccountService.updateForAddMoney(refund.getUserId(), -refund.getTotalFee());
	       insertWXConsumRecords(EncryptHelper.md5(refund.getId().toString()), refund.getUserId(), id, -refund.getTotalFee());
	}

	@Override
	public int countMoneyByUserAndTime(Integer id, Date firstDate, Date sumDate) {
		return this.refundMapper.countMoneyByUserAndTime(id,firstDate,sumDate);
	}

	@Override
	public int countNumByUserAndTime(Integer id, Date firstDate, Date sumDate) {
		return this.refundMapper.countNumByUserAndTime(id,firstDate,sumDate);
	}

	@Override
	public List<Refund> findRefundByShopId(Integer shopId) {
		return this.refundMapper.findRefundByShopId(shopId);
	}

	@Override
	public Refund findRefundById(Integer id) {
		return this.refundMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<HashMap<String, Object>> findItemInfoByOrderNo(Integer id) {
		return this.refundMapper.findItemInfoByOrderNo(id);
	}

	@Override
	public int findRefundNumByMonth(Integer id, Date date) {
		return this.refundMapper.findRefundNumByMonth(id,date);
	}

	@Override
	public Refund findCurrentMonthFreeShopRefundFee(Integer id, Date date2,
			Date date1) {
		return this.refundMapper.findCurrentMonthFreeShopRefundFee(id,date2,date1);
	}

	@Override
	public Long findTotal(String userName, Date startTime, Date endTime,String param, int reseauId, Integer cityId,String itemName) {
		return this.refundMapper.findTotal(userName,startTime,endTime,param,reseauId,cityId,itemName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void createRefundNotUpdateStock(Refund refund) {
		 refund.setState(Constant.VALID_STATUS);
	        refund.setCreatedTime(new Date());
	        refund.setModifiedTime(new Date());
	        this.refundMapper.insert(refund);

	        List<RefundItem> itemList = refund.getRefundItemList();
	    	for(RefundItem refundItem : itemList){
	    		refundItem.setRefundId(refund.getId());
	    		this.refundItemMapper.insert(refundItem);
	    	}
		
	}
	
	public void deleteNotStock(int id){
		   Pair<Refund, List<RefundItem>> pair = this.findById(id);
	       Refund refund = pair.getLeft();
	       refund.setState(Constant.DELETE_STATUS);
	       refund.setModifiedTime(new Date());
	       this.refundMapper.updateByPrimaryKeySelective(refund);

	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void confirm(int id){
		Pair<Refund, List<RefundItem>> pair = this.findById(id);
		Refund refund = pair.getLeft();
		refund.setReason(1);
		this.refundMapper.updateByPrimaryKeySelective(refund);
		List<RefundItem> items = pair.getRight();
		for (RefundItem refundItem : items) {
    		stockService.updateForAdd(refundItem.getItemId(), refundItem.getNum());
    		
    		//throw new RuntimeException("测试事务");
    		if(1==refundItem.getReason() && refundItem.getNum()>=3){
    			ShopBlackList shopBlackList = this.shopBlackListService.findByShopIdAndItemId(refund.getUserId(),refundItem.getItemId());
    			if(null==shopBlackList){
    				ShopBlackList list = new ShopBlackList();
    				list.setItemId(refundItem.getItemId());
    				list.setShopId(refund.getUserId());
    				list.setState(1);
    				list.setType(1);
    				this.shopBlackListService.insert(list);
    			}
    		}
		}
		
	}

	@Override
	public Integer findBeConfirmRefundByCityId(Integer cityId) {
		return this.refundMapper.findBeConfirmRefundByCityId(cityId);
	}

	@Override
	public List<Refund> findByTimeAndCityId(Date start, Date end, Integer cityId) {
		return this.refundMapper.findByTimeAndCityId(start,end,cityId);
	}

	@Override
	public List<Refund> findTranSumRefundsByCityId(Date startTime,
			Date endTime, Integer cityId) {
		RefundExample refundExample = new RefundExample();

        Criteria criteria = refundExample.createCriteria();
        refundExample.setOrderByClause("executed_time asc ");
        criteria.andStateEqualTo(Constant.VALID_STATUS);
        criteria.andCityIdEqualTo(cityId);
        // 开始时间
        if (startTime != null) {
            criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
        }

        // 结束时间
        if (endTime != null) {
            criteria.andExecutedTimeLessThanOrEqualTo(endTime);
        }

        //Map<Integer,Item> itemMap = Maps.newHashMap();

        List<Refund> list = refundMapper.selectByExample(refundExample);

		return list;
	}

	@Override
	public Refund findRefundFeeAndRefundNumByDayAndUserId(Integer userId,
			Date date) {
		return this.refundMapper.findRefundFeeAndRefundNumByDayAndUserId(userId,date);
	}

	
}
