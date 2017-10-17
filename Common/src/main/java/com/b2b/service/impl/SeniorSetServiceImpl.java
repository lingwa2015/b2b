package com.b2b.service.impl;

import com.b2b.common.domain.ShopLayer;
import com.b2b.service.ShopItemService;
import com.b2b.service.ShopLayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.SeniorSetMapper;
import com.b2b.common.domain.SeniorSet;
import com.b2b.service.SeniorSetService;

import java.util.Date;
import java.util.List;

@Service
public class SeniorSetServiceImpl implements SeniorSetService {
	@Autowired
	private SeniorSetMapper seniorSetMapper;

	@Autowired
	private ShopLayerService shopLayerService;

	@Autowired
    private ShopItemService shopItemService;

	@Override
	public SeniorSet findById(Integer shopId) {
		return this.seniorSetMapper.selectByPrimaryKey(shopId);
	}

	@Override
	public void update(SeniorSet set) {
		this.seniorSetMapper.updateByPrimaryKeySelective(set);
	}

	@Override
	public void create(SeniorSet set) {
		this.seniorSetMapper.insert(set);
		if (null!=set.getIslayer() && 1==set.getIslayer()){
			List<ShopLayer> shopLayerList =this.shopLayerService.findByShopIdAndStatus(set.getShopId());
			if (shopLayerList.isEmpty()){
				int id = 0;
			    for (int i = 0; i < 3; i++) {
					ShopLayer layer = new ShopLayer();
					layer.setCreatedTime(new Date());
					if(i==0){
						layer.setName("第一层");
					}else if (i==1){
						layer.setName("第二层");
					}else{
						layer.setName("第三层");
					}
					layer.setShopId(set.getShopId());
					layer.setStatus(1);
					this.shopLayerService.insert(layer);
					if(i==0){
					    id = layer.getId();
                    }
				}
				this.shopItemService.updateDefaultLayerId(set.getShopId(),id);
			}else{
				this.shopItemService.updateLayerIdWhereIsNull(set.getShopId(),shopLayerList.get(0).getId());
			}
		}
	}

	@Override
	public void updateUnselective(SeniorSet set) {
		this.seniorSetMapper.updateByPrimaryKey(set);
        if (null!=set.getIslayer() && 1==set.getIslayer()){
            List<ShopLayer> shopLayerList =this.shopLayerService.findByShopIdAndStatus(set.getShopId());
            if (shopLayerList.isEmpty()){
                int id = 0;
                for (int i = 0; i < 3; i++) {
                    ShopLayer layer = new ShopLayer();
                    layer.setCreatedTime(new Date());
                    if(i==0){
                        layer.setName("第一层");
                    }else if (i==1){
                        layer.setName("第二层");
                    }else{
                        layer.setName("第三层");
                    }
                    layer.setShopId(set.getShopId());
                    layer.setStatus(1);
                    this.shopLayerService.insert(layer);
                    if(i==0){
                        id = layer.getId();
                    }
                }
                this.shopItemService.updateDefaultLayerId(set.getShopId(),id);
            }else{
				this.shopItemService.updateLayerIdWhereIsNull(set.getShopId(),shopLayerList.get(0).getId());
			}
        }
	}
}
