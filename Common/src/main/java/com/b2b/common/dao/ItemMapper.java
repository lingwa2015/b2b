package com.b2b.common.dao;

import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemMapper {
    int countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemExample example);

    Item selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

	void upOrDownrecommend(@Param("id")Integer id,@Param("num") int num,@Param("userid")Integer userid);

	String findVarietyName(int id);
	
	List<Item> selectAutoItem(int week);
	
	List<Item> selectBlackItem(int customerId);
	
	List<Item> selectWhiteItem(@Param("customerId") int customerId,@Param("whitenum") int whitenum);

	void deleteItemVariety(@Param("id")int id, @Param("userId")Integer userId);
	
	List<Item> selectAutoItemByCategoryId(@Param("week")int week,@Param("categoryId")Integer categoryId);

	int countBycatid(Integer catid);

	List<Item> findByCatid(@Param("catid")Integer catid,@Param("pageIndex")Integer pageIndex);

	List<HashMap<String, Object>> findItemNames(@Param("name")String name, @Param("cityId")Integer cityId);

	List<HashMap<String, Object>> findAllWithStockByCatIdAndCityId(@Param("cid")Integer cid,@Param("cityId")Integer cityId);

	List<Item> findItemByCateId(@Param("id")Integer id, @Param("shopId")Integer shopId,
								@Param("isfreeSpecialSupply")Integer isfreeSpecialSupply, @Param("lableId")Integer lableId);

	List<Item> findItemsByLeaveCate(@Param("cid")Integer cid, @Param("oneid")Integer oneid, @Param("shopId")Integer shopId
			, @Param("isfreeSpecialSupply")Integer isfreeSpecialSupply, @Param("lableId")Integer lableId);

	List<Item> findSourcingItemByLikeName(@Param("name")String name, @Param("shopId")Integer shopId, @Param("isfreeSpecialSupply")Integer isfreeSpecialSupply, @Param("cityId")Integer cityId);

	void updateItemScore(@Param("itemId")Integer itemId, @Param("avgScore")double avgScore);

	ArrayList<Integer> findNewItem();

	ArrayList<Integer> findRecommend();

	ArrayList<Integer> findBang();

	List<Item> findByCondition(@Param("sortItem")String sortItem, @Param("grade")String grade, @Param("state")int state,
			@Param("down")int down, @Param("categoryId")Integer categoryId, @Param("itemName")String itemName, @Param("isnew")String isnew,
			@Param("isrecommend")String isrecommend, @Param("start")int start, @Param("pageSize")int pageSize, @Param("categorylevelId")Integer categorylevelId, @Param("cityId")Integer cityId,@Param("free")Integer free);

	int countByCondition(@Param("sortItem")String sortItem, @Param("grade")String grade, @Param("state")int state,
			@Param("down")int down, @Param("categoryId")Integer categoryId, @Param("itemName")String itemName, @Param("isnew")String isnew,
			@Param("isrecommend")String isrecommend, @Param("categorylevelId")Integer categorylevelId, @Param("cityId")Integer cityId,@Param("free")Integer free);

	int countByCondition2(@Param("sortItem")String sortItem, @Param("grade")String grade, @Param("state")int state,
			@Param("categoryId")Integer categoryId, @Param("itemName")String itemName, @Param("isnew")String isnew,
			@Param("isrecommend")String isrecommend,@Param("categorylevelId")Integer categorylevelId, @Param("cityId")Integer cityId,@Param("free")Integer free);

	List<Item> findByCondition2(@Param("sortItem")String sortItem, @Param("grade")String grade, @Param("state")int state,
			@Param("categoryId")Integer categoryId, @Param("itemName")String itemName, @Param("isnew")String isnew,
			@Param("isrecommend")String isrecommend, @Param("start")int start, @Param("pageSize")int pageSize,@Param("categorylevelId")Integer categorylevelId, @Param("cityId")Integer cityId,@Param("free")Integer free);

	List<Item> findSourcingOfficeItemByLikeName(@Param("name")String name, @Param("shopId")Integer shopId, @Param("isfreeSpecialSupply")Integer isfreeSpecialSupply, @Param("cityId")Integer cityId, @Param("catid")Integer catid);

	ArrayList<Integer> findFreeSpecialSupply();

	List<Item> findByLikeName(@Param("name")String name,@Param("cityId") Integer cityId);

	List<HashMap<String, Object>> findItemsByIsDownAndSortItem(@Param("down")int down, @Param("itemName")String itemName, @Param("categoryId")Integer categoryId,
			@Param("categorylevelId")Integer categorylevelId, @Param("grade")String grade, @Param("isnews")String isnews, @Param("isrecommend")String isrecommend, @Param("cityId")Integer cityId,@Param("free")Integer free);

	List<Item> findItemsByIntelligentItems(@Param("shopId")Integer shopId, @Param("isfreeSpecialSupply")Integer isfreeSpecialSupply);

	List<Item> findByItemNameAndCityId(@Param("itemName")String itemName, @Param("cityId")Integer cityId);
}