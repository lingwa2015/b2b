/**
 * 
 */
package com.sxc.web.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sxc.web.AppBaseUnitTest;

/**
 * @author Ming.Zi
 * @dMar 14, 2015
 * @version 1.0
 */
public class AppStoreHouseControllerTest extends AppBaseUnitTest {

	@Autowired
	AppStoreHouseController appStoreHouseController;
	
	@Test
	public void test_getStoreHouseByUserPhone() {
		System.out.println("结果：" + appStoreHouseController.getStoreHouseByUserPhone("13666666666"));
	}
	
	@Test
	public void test_getAllStoreHouses() {
		System.out.println("result of getAllStoreHouses:" + appStoreHouseController.getAllStoreHouses());
	}
}
