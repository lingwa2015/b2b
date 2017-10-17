package com.b2b.common.util;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.b2b.common.domain.OrderItem;

public class OIComparator implements Comparator<OrderItem> {

	@Override
	public int compare(OrderItem o1, OrderItem o2) {
		int max = o1.getPosition().compareTo(o2.getPosition());
		return max;
	}

}
