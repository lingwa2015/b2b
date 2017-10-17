package com.b2b.web.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class FileUtil {

	public static String genUploadFileName(String oldName){
		String prefix = getFilePrefix(oldName);
		String fileName = UUID.randomUUID().toString();
		if(StringUtils.isNotBlank(prefix)){
			fileName = fileName +"."+prefix;
		}
		
		return fileName;
	}
	
	public static String getFilePrefix(String fileName){
		String prefix = "";
		int index = fileName.lastIndexOf(".");
		if(index!=-1){
			prefix = fileName.substring(index+1);
		}
		return prefix;
	}
	
	public static void main(String[] args) {
		System.err.println(genUploadFileName("1.txt"));
	}
}
