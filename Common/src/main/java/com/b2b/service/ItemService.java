package com.b2b.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemTaste;
import com.b2b.common.domain.PersonUser;
import com.b2b.page.Page;

public interface ItemService {
	
	public Page<Item> findPage(Item item , int currentPage ,int pageSize,int businessId,String itemSort, String grade, String isnew, String isrecommend, Integer cityId);
	
	public List<Item> findAll();
	
	public List<Item> findCatIdAll(int catId,int levelCatId,String itemName);
	
	public List<Item> findIsDown(int down,String sortItem) ;
	
	public List<Item> findAllWithStock();
	
	public List<Item> findAllWithStockNoIngoreIsdown();
	
	public List<Item> findAll(int businessId);
	
	public void create(Item dto, List<ItemTaste> itemTaste,List<Integer> list);
	
	public void update(Item dto, List<ItemTaste> itemTaste,List<Integer> list);
	
	public void delete(int id);
	
	public Item findById(int id);
	
	public void updateDown(int id,int down, PersonUser personUser);
	
	public Page<Item> findPageByIsDown(Item item , int currentPage ,int pageSize,int businessId,int down,String itemSort, String grade, String isnew, String isrecommend, Integer cityId);

	public List<HashMap<String, Object>> findItemsByIsDownAndSortItem(int down,
			String itemName, Integer categoryId, Integer categorylevelId, String grade, String isnews, String isrecommend, Integer cityId,Integer free);

	public void upOrDownrecommend(Integer id, int num,Integer userid);

	public String findVarietyName(int id);
	
	public List<Item> selectAutoItem(int week);
	
	public List<Item> selectAutoItemByCategoryId(int week,int categoryId);
	
	public List<Item> selectBlackItem(int customerId);
	
	public List<Item> selectWhiteItem(int customerId,int whitenum);

	public void deleteItemVariety(int id, Integer userId);

	public Page<Item> findPageByStatus(Item item, int currentPage,
			int pageSize, int businessId, String sortItem, String grade, String isnew, String isrecommend, Integer cityId);

	public void recover(int id, PersonUser personUser);
	
	//根据类目查
	public int count(Integer catid);

	public List<Item> findByCatid(Integer catid, Integer pageIndex);

	public List<HashMap<String, Object>> findItemNames(String itemName, Integer cityId);

	public List<Item> findByItemName(String parameter);

	public List<HashMap<String, Object>> findAllWithStockByCatIdAndCityId(Integer id,Integer cityId);

	public List<Item> findItemByCateId(Integer id, Integer shopId, Integer isfreeSpecialSupply, Integer lableId);

	public List<Item> findItemsByLeaveCate(Integer cid, Integer oneid, Integer shopId, Integer isfreeSpecialSupply, Integer lableId);

	public List<Item> findSourcingItemByLikeName(String name, Integer shopId, Integer isfreeSpecialSupply, Integer cityId);

	public void updateItemScore(Integer itemId, double avgScore);

	public ArrayList<Integer> findNewItem();

	public ArrayList<Integer> findRecommend();

	public ArrayList<Integer> findBang();
	
	public List<Item> findSourcingOfficeItemByLikeName(String name, Integer shopId, Integer isfreeSpecialSupply, Integer cityId, Integer catid);

	public ArrayList<Integer> findFreeSpecialSupply();

	public List<Item> findByLikeName(String name, Integer cityId);

	public List<Item> findAllWithStockByCityId(Integer cityId);

	public List<Item> findAllWithStockAndBarcodeByCityId(int cityId);

	List<Item> findItemsByIntelligentItems(Integer shopId, Integer isfreeSpecialSupply);

    Item findByItemNameAndCityId(String itemName, Integer cityId);
}
