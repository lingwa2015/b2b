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
public class AppItemControllerTest extends AppBaseUnitTest {

	@Autowired
	AppItemController appItemController;
	
	@Test
	public void test_getAllItem() {
		System.out.println("result: " + appItemController.getAllItem(null, null));
	}
}
