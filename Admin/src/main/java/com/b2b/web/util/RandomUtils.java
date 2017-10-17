package com.b2b.web.util;

import java.util.Random;
public class RandomUtils {
	 private static Random random;
	 
	    //双重校验锁获取一个Random单例
	    public static Random getRandom() {
	        if(random==null){
	            synchronized (RandomUtils.class) {
	                if(random==null){
	                    random =new Random();
	                }
	            }
	        }
	         
	        return random;
	    }
	 
	    /**
	     * 获得一个[0,max)之间的整数。
	     * @param max
	     * @return
	     */
	    public static int getRandomInt(int max) {
	        return Math.abs(getRandom().nextInt())%max;
	    }
	     
	    /**
	     * 获得一个[0,max)之间的整数。
	     * @param max
	     * @return
	     */
	    public static long getRandomLong(long max) {
	        return Math.abs(getRandom().nextInt())%max;
	    }
}
