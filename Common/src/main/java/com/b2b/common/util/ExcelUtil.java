package com.b2b.common.util;

import java.io.InputStream;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class ExcelUtil {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ExcelUtil.class);

	
	public static List<List<String>> readExcel(InputStream input){
		List<List<String>> list = Lists.newArrayList();
		 Workbook rwb = null;
		 
		 try{
			 rwb = Workbook.getWorkbook(input);
			 Sheet sheet = rwb.getSheet(0);  
			 
			 int rows = sheet.getRows();
			 int cols = sheet.getColumns();
			 
			 for(int i=0;i<rows;i++){
				 List<String> lineList = Lists.newArrayList();
				 for(int j=0;j<cols;j++){
					 Cell cell = sheet.getCell(j,i);    
				     String content = cell.getContents();
				     
				     lineList.add(content);
				 }
				 
				 list.add(lineList);
			 }
		 }catch(Exception e){
			 logger.error("读取excel失败",e);
			 throw new RuntimeException(e);
		 }finally{
			 try{
				 if(rwb!=null){
					 rwb.close();
				 }
				 
				 if(input!=null){
					 input.close();
				 }
			 }catch(Exception e){
				 logger.error(e.getMessage(),e);
			 }
		 }
		return list;
	}
}
