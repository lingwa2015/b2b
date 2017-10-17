package com.b2b.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.Constant;
import com.b2b.common.dao.ItemMapper;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.ItemExample;
import com.b2b.common.domain.ItemExample.Criteria;
import com.b2b.common.domain.ItemSupplier;
import com.b2b.common.domain.ItemTaste;
import com.b2b.common.domain.Stock;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.service.ItemSupplierService;
import com.b2b.service.ItemTasteService;
import com.b2b.service.ShopBlackListService;
import com.b2b.service.StockService;
import com.b2b.service.SupplierService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemMapper itemMapper;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	StockService stockService;
	@Autowired
	private ItemTasteService itemTasteService;
	
	@Autowired
	private ItemSupplierService itemSupplierService;
	
	@Autowired
	private ShopBlackListService shopBlackListService;

	@Override
	public void create(Item dto,List<ItemTaste> itemTaste,List<Integer> list) {

		dto.setStatus(Constant.VALID_STATUS);
		dto.setProfit(getProfit(dto));
		int id = itemMapper.insert(dto);

		//创建一个空的库存
		Stock stock = new Stock();
    	stock.setItemName(dto.getItemName());
    	stock.setItemId(dto.getId());
    	stock.setNum(0);
    	if(1==dto.getProperty()){
    		if(5>dto.getSaleSizeNum()){
    			stock.setAlertNum(5);
    		}else{
    			stock.setAlertNum(dto.getSaleSizeNum());
    		}
    	}else{
    		stock.setAlertNum(50);
    	}
    	stockService.create(stock);
    	if(!itemTaste.isEmpty()){
    		for (ItemTaste taste : itemTaste) {
    			taste.setItemId(dto.getId());
    			ItemTaste exist = this.itemTasteService.findByNameAndItemId(dto.getId(),taste.getTasteName(),taste.getBarcode());
    			if(exist!=null){
    				continue;
    			}
    			this.itemTasteService.created(taste);
			}
    	}
    	if (!list.isEmpty()) {
			for (Integer supid : list) {
				ItemSupplier supplier = new ItemSupplier();
				supplier.setItemId(dto.getId());
				supplier.setSuppilerId(supid);
				this.itemSupplierService.save(supplier);
			}
		}
    	
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void update(Item dto,List<ItemTaste> itemTaste,List<Integer> list) {
		dto.setStatus(Constant.VALID_STATUS);
		dto.setProfit(getProfit(dto));
		itemMapper.updateByPrimaryKeySelective(dto);
		this.itemTasteService.deleteAllByItemId(dto.getId());
		if(!itemTaste.isEmpty()){
			for (ItemTaste taste : itemTaste) {
				taste.setItemId(dto.getId());
    			ItemTaste exist = this.itemTasteService.findByNameAndItemId(dto.getId(),taste.getTasteName(),taste.getBarcode());
    			if(exist!=null){
    				continue;
    			}
    			this.itemTasteService.created(taste);
			}
		}
        //更新库存表里面的商品名称
        Stock stock = stockService.findByItem(dto.getId());
        if(stock!=null){
        	stock.setItemName(dto.getItemName());
        	stockService.update(stock);
        }
        this.itemSupplierService.deleteByItemId(dto.getId());
        if (!list.isEmpty()) {
			for (Integer supid : list) {
				ItemSupplier supplier = new ItemSupplier();
				supplier.setItemId(dto.getId());
				supplier.setSuppilerId(supid);
				this.itemSupplierService.save(supplier);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void updateDown(int id,int isdown,PersonUser personUser) {
		Item dto = this.findById(id);
		dto.setIsdown(isdown);
		dto.setUpdatedTime(new Date());
		dto.setUpdatedUserid(personUser.getId());
		itemMapper.updateByPrimaryKey(dto);
	}


	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void delete(int id) {

		Item dto = this.findById(id);
		if(dto!=null){
			dto.setStatus(Constant.DELETE_STATUS);
			itemMapper.updateByPrimaryKey(dto);
			stockService.deleteByItemId(id);
		}

	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void recover(int id,PersonUser personUser) {
		Item dto = this.findById(id);
		if(dto!=null){
			dto.setStatus(Constant.VALID_STATUS);
			dto.setIsdown(1);
			dto.setUpdatedTime(new Date());
			dto.setUpdatedUserid(personUser.getId());
			itemMapper.updateByPrimaryKey(dto);
		}
	}

	@Override
	public Item findById(int id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Item> findPage(Item item, int currentPage, int pageSize,int businessId,String itemSort, String grade,String isnew,String isrecommend, Integer cityId) {
		return findPageCommon(item,currentPage,pageSize,businessId,0,itemSort,grade,isnew,isrecommend,cityId);
	}

	@Override
	public Page<Item> findPageByIsDown(Item item , int currentPage ,int pageSize,int businessId,int down,String itemSort, String grade,String isnew,String isrecommend, Integer cityId) {
		return findPageCommon(item,currentPage,pageSize,businessId,down,itemSort,grade,isnew,isrecommend,cityId);
	}
	

	
	@Override
	public Page<Item> findPageByStatus(Item item, int currentPage,
			int pageSize, int businessId, String sortItem, String grade,String isnew,String isrecommend, Integer cityId) {
		Integer categoryId = item.getCategoryId();
		Integer categorylevelId = item.getCategorylevelId();
		String itemName = item.getItemName();
		Integer freeSpecialSupply = item.getFreeSpecialSupply();
		int count = itemMapper.countByCondition2(sortItem,grade,Constant.DELETE_STATUS,categoryId,itemName,isnew,isrecommend,categorylevelId,cityId,freeSpecialSupply);
		if(count ==0){
			return new Page<Item>();
		}

		int start = Page.calStartRow(currentPage, pageSize);

		List<Item> list =this.itemMapper.findByCondition2(sortItem,grade,Constant.DELETE_STATUS,categoryId,itemName,isnew,isrecommend,start,pageSize,categorylevelId,cityId,freeSpecialSupply);

		//填充类目
		List<ItemCategory> cateList =itemCategoryService.findAllOneCatsByCityId(cityId);
		List<ItemCategory> cateList2 = itemCategoryService.findAllTwoCatsByCityId(cityId);
		//List<ItemTaste> itemTaste = this.itemTasteService.findAll();
		if(CollectionUtils.isNotEmpty(list)){
			if(CollectionUtils.isNotEmpty(cateList)){
				for(Item dto:list){
					int cateId = dto.getCategoryId();
					for(ItemCategory cate : cateList){
						if(cate.getId().equals(cateId)){
							dto.setCategoryName(cate.getCategoryName());
							break;
						}
					}
					Integer leavecat = dto.getCategorylevelId();
					for (ItemCategory cat2 : cateList2) {
						if(cat2.getId().equals(leavecat)){
							dto.setCategoryLeaveName(cat2.getCategoryName());
							break;
						}
					}
					int num = this.shopBlackListService.countByItemId(dto.getId());
					dto.setBlacktotalnum(num);
//					ArrayList<String> array = new ArrayList<String>();
//					for (ItemTaste taste : itemTaste) {
//						if(dto.getId()==taste.getItemId()){
//							array.add(taste.getTasteName());
//						}
//					}
//					dto.setTaste(array.toString());
				}
			}
		}
		Page<Item> page = new Page<Item>(currentPage,count,pageSize,list);

		return page;
	}
	
	public Page<Item> findPageCommon(Item item, int currentPage, int pageSize,int businessId,int down,String sortItem,String grade,String isnew,String isrecommend,Integer cityId) {
		Integer categoryId = item.getCategoryId();
		Integer categorylevelId = item.getCategorylevelId();
		String itemName = item.getItemName();
		Integer freeSpecialSupply = item.getFreeSpecialSupply();

		int count = itemMapper.countByCondition(sortItem,grade,Constant.VALID_STATUS,down,categoryId,itemName,isnew,isrecommend,categorylevelId,cityId,freeSpecialSupply);
		if(count ==0){
			return new Page<Item>();
		}

		int start = Page.calStartRow(currentPage, pageSize);
		
		List<Item> list =this.itemMapper.findByCondition(sortItem,grade,Constant.VALID_STATUS,down,categoryId,itemName,isnew,isrecommend,start,pageSize,categorylevelId,cityId,freeSpecialSupply);

		//填充类目
		List<ItemCategory> cateList =itemCategoryService.findAllOneCatsByCityId(cityId);
		List<ItemCategory> cateList2 = itemCategoryService.findAllTwoCatsByCityId(cityId);
		//List<ItemTaste> itemTaste = this.itemTasteService.findAll();
		if(CollectionUtils.isNotEmpty(list)){
			if(CollectionUtils.isNotEmpty(cateList)){
				for(Item dto:list){
					int cateId = dto.getCategoryId();
					for(ItemCategory cate : cateList){
						if(cate.getId().equals(cateId)){
							dto.setCategoryName(cate.getCategoryName());
							break;
						}
					}
					Integer leavecat = dto.getCategorylevelId();
					for (ItemCategory cat2 : cateList2) {
						if(cat2.getId().equals(leavecat)){
							dto.setCategoryLeaveName(cat2.getCategoryName());
							break;
						}
					}
					int num = this.shopBlackListService.countByItemId(dto.getId());
					dto.setBlacktotalnum(num);
//					int itemId = dto.getId();
//					ArrayList<String> array = new ArrayList<String>();
//					for (ItemTaste taste : itemTaste) {
//						if(itemId==taste.getItemId()){
//							array.add(taste.getTasteName());
//						}
//					}
//					dto.setTaste(array.toString());
				}
			}
		}
		Page<Item> page = new Page<Item>(currentPage,count,pageSize,list);

		return page;
	}

	@Override
	public List<Item> findAll() {
		ItemExample example = new ItemExample ();
		example.setOrderByClause(" category_id,convert(item_name using gbk) ");
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andIsdownEqualTo(0);
		return itemMapper.selectByExample(example);
	}

	@Override
	public List<Item> findCatIdAll(int catId,int levelCatId,String itemName) {
		ItemExample example = new ItemExample ();
		example.setOrderByClause(" convert(item_name using gbk) ");
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andIsdownEqualTo(0);
		if(catId!=0){
			criteria.andCategoryIdEqualTo(catId);
		}
		if(levelCatId!=0){
			criteria.andCategorylevelIdEqualTo(levelCatId);
		}
		if(StringUtils.isNotBlank(itemName)){
			criteria.andItemNameLike("%"+itemName+"%");
		}
		return itemMapper.selectByExample(example);
	}


	@Override
	public List<Item> findIsDown(int down,String sortItem) {
		ItemExample example = new ItemExample ();
		if(sortItem!=null ){
			if(sortItem!="item_name"){
				example.setOrderByClause(sortItem+" desc ");
			}else{
				example.setOrderByClause("created_time desc");
			}
		}
		Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Constant.VALID_STATUS);
        criteria.andIsdownEqualTo(down);
		return itemMapper.selectByExample(example);
	}

	@Override
	public List<Item> findAllWithStock() {
		ItemExample ie = new ItemExample();
		ie.setOrderByClause("isdown, convert(item_name using gbk) ");
		ie.createCriteria().andStatusEqualTo(Constant.VALID_STATUS);
		List<Item> list = itemMapper.selectByExample(ie);
		if(CollectionUtils.isNotEmpty(list)){
			Set<Integer> itemIds = new HashSet<Integer>();
			for(Item item: list){
				item.setItemName(item.getItemName()+"【"+item.getSize()+"】");
				itemIds.add(item.getId());
				Date date = item.getCreatedTime();
				long diff = DateUtil.diff(date, new Date(), Calendar.DATE);
				if(diff<=30){
					item.setIsnew(1);
				}else{
					item.setIsnew(0);
				}
			}
			//List<Stock> stocks = stockService.findByItem(new ArrayList<Integer>(itemIds));
			//12.8号修改,修复下单时,架下商品库存为0
			List<Stock> stocks = stockService.findByItemId(new ArrayList<Integer>(itemIds));
			if (CollectionUtils.isNotEmpty(stocks)) {
				Map<Integer, Stock> map = new HashMap<Integer, Stock>();
				for (Stock stock : stocks) {
					map.put(stock.getItemId(), stock);
				}
				for (Item item: list) {
					if (map.containsKey(item.getId())) {
						item.setStock(map.get(item.getId()).getNum());
					}
				}
			}
		}
		return list;
	}
	
//	@Override
//	public List<Item> findAllWithStock() {
//		
//	}
	
	@Override
	public List<Item> findAllWithStockNoIngoreIsdown() {
		ItemExample ie = new ItemExample();
		ie.setOrderByClause(" convert(item_name using gbk) ");
		ie.createCriteria().andStatusEqualTo(Constant.VALID_STATUS).andIsdownEqualTo(0);
		List<Item> list = itemMapper.selectByExample(ie);
		if(CollectionUtils.isNotEmpty(list)){
			Set<Integer> itemIds = new HashSet<Integer>();
			for(Item item: list){
				item.setItemName(item.getItemName()+"【"+item.getSize()+"】");
				itemIds.add(item.getId());
			}
			List<Stock> stocks = stockService.findByItem(new ArrayList<Integer>(itemIds));
			if (CollectionUtils.isNotEmpty(stocks)) {
				Map<Integer, Stock> map = new HashMap<Integer, Stock>();
				for (Stock stock : stocks) {
					map.put(stock.getItemId(), stock);
				}
				for (Item item: list) {
					if (map.containsKey(item.getId())) {
						item.setStock(map.get(item.getId()).getNum());
					}
				}
			}
		}
		return list;
	}
	

	@Override
	public List<Item> findAll(int businessId) {
		if(businessId==0)
			return findAll();
		ItemExample itemExample = new ItemExample();
		Criteria criteria = itemExample.createCriteria();
        criteria.andStatusEqualTo(Constant.VALID_STATUS);
        List<ItemCategory> cateList=itemCategoryService.findByBusinessId(businessId);
		if(cateList.size()==0)
			return new ArrayList<Item>();
		List<Integer> itemCategoryIds=new ArrayList<Integer>();
		for (ItemCategory itemCategory : cateList) {
			itemCategoryIds.add(itemCategory.getId());
		}
		criteria.andCategoryIdIn(itemCategoryIds);
		List<Item> list = itemMapper.selectByExample(itemExample);
		return list;
	}

	public BigDecimal getProfit(Item dto)
	{
		BigDecimal profit=new BigDecimal(0);
		double price=(double)dto.getPrice();
		double costPrice=(double)dto.getCostPrice();
		double profitPrice=((price-costPrice)/price)*100;
		profit=new BigDecimal(profitPrice);
		return profit.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	

	@Override
	public void upOrDownrecommend(Integer id, int num,Integer userid) {
		this.itemMapper.upOrDownrecommend(id,num,userid);
	}

	@Override
	public String findVarietyName(int id) {
		return this.itemMapper.findVarietyName(id);
	}
	
	@Override
	public List<Item> selectAutoItem(int week){
		return itemMapper.selectAutoItem(week);
	}
	
	@Override
	public List<Item> selectAutoItemByCategoryId(int week,int categoryId){
		return itemMapper.selectAutoItemByCategoryId(week,categoryId);
	}

	@Override
	public List<Item> selectBlackItem(int customerId) {
		return itemMapper.selectBlackItem(customerId);
	}

	@Override
	public List<Item> selectWhiteItem(int customerId, int whitenum) {
		return itemMapper.selectWhiteItem(customerId,whitenum);
	}

	@Override
	public void deleteItemVariety(int id,Integer userId) {
		this.itemMapper.deleteItemVariety(id,userId);
	}

	@Override
	public int count(Integer catid) {
		return this.itemMapper.countBycatid(catid);
	}

	@Override
	public List<Item> findByCatid(Integer catid,Integer pageIndex) {
		return this.itemMapper.findByCatid(catid,pageIndex);
	}

	@Override
	public List<HashMap<String, Object>> findItemNames(String name,Integer cityId) {
		return this.itemMapper.findItemNames(name,cityId);
	}

	@Override
	public List<Item> findByItemName(String parameter) {
		ItemExample example = new ItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andItemNameEqualTo(parameter);
		return this.itemMapper.selectByExample(example);
	}

	@Override
	public List<HashMap<String, Object>> findAllWithStockByCatIdAndCityId(Integer id,Integer cityId) {
		return this.itemMapper.findAllWithStockByCatIdAndCityId(id,cityId);
	}

	@Override
	public List<Item> findItemByCateId(Integer id,Integer shopId,Integer isfreeSpecialSupply, Integer lableId) {
		return this.itemMapper.findItemByCateId(id,shopId,isfreeSpecialSupply, lableId);
	}

	@Override
	public List<Item> findItemsByLeaveCate(Integer cid,Integer oneid,Integer shopId,Integer isfreeSpecialSupply, Integer lableId) {
		return this.itemMapper.findItemsByLeaveCate(cid,oneid,shopId,isfreeSpecialSupply,lableId);
	}

	@Override
	public List<Item> findSourcingItemByLikeName(String name,Integer shopId,Integer isfreeSpecialSupply,Integer cityId) {
		return this.itemMapper.findSourcingItemByLikeName(name,shopId,isfreeSpecialSupply,cityId);
	}

	@Override
	public void updateItemScore(Integer itemId, double avgScore) {
		this.itemMapper.updateItemScore(itemId,avgScore);
	}

	@Override
	public ArrayList<Integer> findNewItem() {
		return this.itemMapper.findNewItem();
	}

	@Override
	public ArrayList<Integer> findRecommend() {
		return this.itemMapper.findRecommend();
	}

	@Override
	public ArrayList<Integer> findBang() {
		return this.itemMapper.findBang();
	}

	@Override
	public List<Item> findSourcingOfficeItemByLikeName(String name,Integer shopId,Integer isfreeSpecialSupply,Integer cityId, Integer catid) {
		return this.itemMapper.findSourcingOfficeItemByLikeName(name,shopId,isfreeSpecialSupply,cityId,catid);
	}

	@Override
	public ArrayList<Integer> findFreeSpecialSupply() {
		return this.itemMapper.findFreeSpecialSupply();
	}

	@Override
	public List<Item> findByLikeName(String name,Integer cityId) {
		return this.itemMapper.findByLikeName(name,cityId);
	}

	@Override
	public List<Item> findAllWithStockByCityId(Integer cityId) {
		ItemExample ie = new ItemExample();
		ie.setOrderByClause("isdown, convert(item_name using gbk) ");
		Criteria criteria = ie.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andCityIdEqualTo(cityId);
		List<Item> list = itemMapper.selectByExample(ie);
		if(CollectionUtils.isNotEmpty(list)){
			Set<Integer> itemIds = new HashSet<Integer>();
			for(Item item: list){
				item.setItemName(item.getItemName()+"【"+item.getSize()+"】");
				itemIds.add(item.getId());
				Date date = item.getCreatedTime();
				long diff = DateUtil.diff(date, new Date(), Calendar.DATE);
				if(diff<=30){
					item.setIsnew(1);
				}else{
					item.setIsnew(0);
				}
			}
			//List<Stock> stocks = stockService.findByItem(new ArrayList<Integer>(itemIds));
			//12.8号修改,修复下单时,架下商品库存为0
			List<Stock> stocks = stockService.findByItemId(new ArrayList<Integer>(itemIds));
			if (CollectionUtils.isNotEmpty(stocks)) {
				Map<Integer, Stock> map = new HashMap<Integer, Stock>();
				for (Stock stock : stocks) {
					map.put(stock.getItemId(), stock);
				}
				for (Item item: list) {
					if (map.containsKey(item.getId())) {
						item.setStock(map.get(item.getId()).getNum());
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<Item> findAllWithStockAndBarcodeByCityId(int cityId) {
		ItemExample ie = new ItemExample();
		ie.setOrderByClause("isdown, convert(item_name using gbk) ");
		Criteria criteria = ie.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andCityIdEqualTo(cityId);
		List<Item> list = itemMapper.selectByExample(ie);
		if(CollectionUtils.isNotEmpty(list)){
			Set<Integer> itemIds = new HashSet<Integer>();
			for(Item item: list){
				if(null ==item.getBarcode()){
					item.setItemName(item.getItemName()+"【"+item.getSize()+"】");
				}else{
					item.setItemName(item.getItemName()+"【"+item.getSize()+"】"+"【"+item.getBarcode()+"】");
				}
				itemIds.add(item.getId());
				Date date = item.getCreatedTime();
				long diff = DateUtil.diff(date, new Date(), Calendar.DATE);
				if(diff<=30){
					item.setIsnew(1);
				}else{
					item.setIsnew(0);
				}
			}
			//List<Stock> stocks = stockService.findByItem(new ArrayList<Integer>(itemIds));
			//12.8号修改,修复下单时,架下商品库存为0
			List<Stock> stocks = stockService.findByItemId(new ArrayList<Integer>(itemIds));
			if (CollectionUtils.isNotEmpty(stocks)) {
				Map<Integer, Stock> map = new HashMap<Integer, Stock>();
				for (Stock stock : stocks) {
					map.put(stock.getItemId(), stock);
				}
				for (Item item: list) {
					if (map.containsKey(item.getId())) {
						item.setStock(map.get(item.getId()).getNum());
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<Item> findItemsByIntelligentItems(Integer shopId, Integer isfreeSpecialSupply) {
		return this.itemMapper.findItemsByIntelligentItems(shopId, isfreeSpecialSupply);
	}

	@Override
	public Item findByItemNameAndCityId(String itemName, Integer cityId) {
		List<Item> items = this.itemMapper.findByItemNameAndCityId(itemName, cityId);
		if (items.size() != 0) {
			return items.get(0);
		}
		return null;
	}

	@Override
	public List<HashMap<String, Object>> findItemsByIsDownAndSortItem(int down, String itemName, Integer categoryId,
			Integer categorylevelId, String grade, String isnews, String isrecommend, Integer cityId,Integer free) {
		return this.itemMapper.findItemsByIsDownAndSortItem(down,itemName,categoryId,categorylevelId,grade,isnews,isrecommend,cityId,free);
	}
	
}
