package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.StandardOrderItem;

public interface StandardOrderItemService {

	List<StandardOrderItem> findByStandOrderId(Integer snackpackageId);

}
