package com.b2b.service.impl;

import com.b2b.Constant;
import com.b2b.common.dao.OrderItemMapper;
import com.b2b.common.dao.OrderMapper;
import com.b2b.common.dao.OutstockItemMapper;
import com.b2b.common.dao.RefundItemMapper;
import com.b2b.common.domain.*;
import com.b2b.common.domain.OrderExample.Criteria;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.OIComparator;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OutstockItemServiceImpl implements OutstockItemService {

	private static final Logger logger = LoggerFactory
			.getLogger(OutstockItemServiceImpl.class);

	@Autowired
	OutstockItemMapper outstockItemMapper;


    @Override
    public int insert(OutstockItem outstockItem) {
    	return this.outstockItemMapper.insert(outstockItem);
	}



	
}
