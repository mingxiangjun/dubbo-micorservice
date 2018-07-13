package com.ming.dubbo.microservice.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;

import com.run.pls.server.core.tableparser.utils.EntityMapUtil;


/**
 * 
 * @author liguowei 2015-11-10 上午11:49:23  ExportUtil.java
 * 导出工具类
 *
 */

public class ExportUtil {
	/**
	 * 20151202liguowei
	 * 导出excel  一个excel多个sheet
	 * @param exportDataMap
	 * @param filename
	 * @param response
	 */
	public static void exportDataMix(Map<String,List<List<String>>> exportDataMap,String filename,HttpServletResponse response){
        try {   
        	filename = new String(filename.getBytes("GBK"), "ISO-8859-1");
        	response.setContentType("application/vnd.ms-excel");  
        	response.setHeader("Content-disposition", "attachment;filename="+filename);  
     		OutputStream ouputStream = response.getOutputStream();
     		WritableWorkbook workbook = Workbook.createWorkbook(ouputStream);
        	
     		int sheetNum = 0;
            for (Entry<String, List<List<String>>> exportDataRntry : exportDataMap.entrySet()) {
            	
	            List<List<String>> exportDataList = exportDataRntry.getValue();
	            String sheetName = EntityMapUtil.getDisplayObjectEntityByName(exportDataRntry.getKey());
	            if(StringUtils.isBlank(sheetName)){
	            	sheetName = "数据导出";
	            }
	  	            
	  	        WritableSheet sheet = workbook.createSheet(sheetName, sheetNum);
	  	        
	            
	        	//设置边框
	    		WritableCellFormat wcfTitle = new WritableCellFormat();
	    		//wcfTitle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
	    		//自动换行
	    		//wcfTitle.setWrap(true);
	    		//设置自动大小
	    		CellView cellView = new CellView();
	    		cellView.setAutosize(true);
	    		sheet.getSettings().setAutomaticFormulaCalculation(true);
	    		
	            //表头 表体
	            int row = 0;
	            if(null != exportDataList && exportDataList.size()!=0){
	            	for (int i = 0; i < exportDataList.size(); i++) {
	            		    for (int j = 0; j < exportDataList.get(i).size(); j++) {
	            		    	sheet.addCell(new Label(j, row, exportDataList.get(i).get(j),wcfTitle));
							}
	            		    row++;
					}
	            }
	            sheetNum++;
            }
           workbook.write();
           workbook.close();
           ouputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }   
	}
	
	/**
	 * 根据结果生成Excel文件
	 * @param exportDataMap
	 * @param filename
	 * @param path
	 */
	public static void exportDataMix(Map<String,List<List<String>>> exportDataMap,String filename,String path){
        try {
        	if (exportDataMap == null || exportDataMap.size() == 0) {
				return ;
			}
        	// 生成路径
        	File filePath = new File(path);
        	if (!filePath.exists()) {
				filePath.mkdirs();
			}
        	// 输出Excel
        	WritableWorkbook workbook = Workbook.createWorkbook(new File(path+File.separator+filename));
     		// 建立表头
     		WritableSheet ws = workbook.createSheet("目录",0);
     		ws.addCell(new Label(0, 0, "导出结果目录"));
     		// 建立其他导出数据
     		int sheetNum = 1;
            for (Entry<String, List<List<String>>> exportDataRntry : exportDataMap.entrySet()) {
	            List<List<String>> exportDataList = exportDataRntry.getValue();
				String sheetName = EntityMapUtil.getResultLogDisplayByName(exportDataRntry.getKey());
	            if(StringUtils.isBlank(sheetName)){
	            	sheetName = "数据导出";
	            }
	  	        WritableSheet sheet = workbook.createSheet(sheetName, sheetNum);
	  	        // 添加超链接到首页目录
	  	        ws.addHyperlink(new WritableHyperlink(1, sheetNum, sheetName, sheet, 0, 0));
	        	//设置边框
	    		WritableCellFormat wcfTitle = new WritableCellFormat();
	    		wcfTitle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
	    		//自动换行
	    		//wcfTitle.setWrap(true);
	    		//设置自动大小
	    		CellView cellView = new CellView();
	    		cellView.setAutosize(true);
	    		sheet.getSettings().setAutomaticFormulaCalculation(true);
	    		
	            //表头 表体
	            int row = 0;
	            if(null != exportDataList && exportDataList.size()!=0){
	            	for (int i = 0; i < exportDataList.size(); i++) {
	            		    for (int j = 0; j < exportDataList.get(i).size(); j++) {
	            		    	sheet.addCell(new Label(j, row, exportDataList.get(i).get(j),wcfTitle));
							}
	            		    row++;
					}
	            }
	            sheetNum++;
            }
           workbook.write();
           workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
	}
	/**
	 * 20151202  liguowei
	 * 生成文件到指定路径  一个excel多个sheet
	 * @param exportDataList
	 * @param filename
	 * @param response
	 * @param path
	 */
	public static void exportDataToPathMix(Map<String,List<List<String>>> exportDataMap,String filename,HttpServletResponse response,String path){
        try {   
        	//filename = new String(filename.getBytes("GBK"), "ISO-8859-1");

        	if(!(new File(path).exists())){
			}
        	new File(path).mkdirs();
            WritableWorkbook workbook = Workbook.createWorkbook(new File(path+filename));
            //sheet页
            int sheetNum = 0;
            for (Entry<String, List<List<String>>> exportDataRntry : exportDataMap.entrySet()) {
				
	            List<List<String>> exportDataList = exportDataRntry.getValue();
	            String sheetName = EntityMapUtil.getDisplayObjectEntityByName(exportDataRntry.getKey());
	            if(StringUtils.isBlank(sheetName)){
	            	sheetName = "数据导出";
	            }
	            
	            WritableSheet sheet = workbook.createSheet(sheetName, sheetNum);
	            
	        	//设置边框
	    		WritableCellFormat wcfTitle = new WritableCellFormat();
	    		//wcfTitle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
	    		//自动换行
	    		//wcfTitle.setWrap(true);
	    		//设置自动大小
	    		CellView cellView = new CellView();
	    		cellView.setAutosize(true);
	    		sheet.getSettings().setAutomaticFormulaCalculation(true);
	    		
	            // 表头 表体
	            int row = 0;
	            if(null != exportDataList && exportDataList.size()!=0){
	            	for (int i = 0; i < exportDataList.size(); i++) {
	            		    for (int j = 0; j < exportDataList.get(i).size(); j++) {
	            		    	sheet.addCell(new Label(j, row, exportDataList.get(i).get(j)==null?"": exportDataList.get(i).get(j),wcfTitle));
							}
	            		    row++;
					}
	            }
	            sheetNum++;
            }
           workbook.write();
           workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  
     
	}
	
	
	
	/**
	 * 20151109 liguowei
	 * 导出excel
	 * @param exportDataList
	 * @param filename
	 * @param response
	 */
	public static void exportData(List<List<String>> exportDataList,String filename,HttpServletResponse response){
        try {   
        	filename = new String(filename.getBytes("GBK"), "ISO-8859-1");
        	response.setContentType("application/vnd.ms-excel");  
        	response.setHeader("Content-disposition", "attachment;filename="+filename);  
     		OutputStream ouputStream = response.getOutputStream();
        	
            WritableWorkbook workbook = Workbook.createWorkbook(ouputStream);
            WritableSheet sheet = workbook.createSheet("数据列表", 0);
            
        	//设置边框
    		WritableCellFormat wcfTitle = new WritableCellFormat();
    		//wcfTitle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
    		//自动换行
    		//wcfTitle.setWrap(true);
    		//设置自动大小
    		CellView cellView = new CellView();
    		cellView.setAutosize(true);
    		sheet.getSettings().setAutomaticFormulaCalculation(true);
    		
    		
            // 表头
    	   if(null != exportDataList && exportDataList.size()!=0){
    		for (int i = 0; i <  exportDataList.get(0).size(); i++) {
    			  sheet.addCell(new Label(i, 0, exportDataList.get(0).get(i),wcfTitle));
			}
    	   }
            //表体
            int row = 1;
            if(null != exportDataList && exportDataList.size()!=0){
            	for (int i = 1; i < exportDataList.size(); i++) {
            		    for (int j = 0; j < exportDataList.get(i).size(); j++) {
            		    	sheet.addCell(new Label(j, row, exportDataList.get(i).get(j),wcfTitle));
						}
            		    row++;
				}
            }
           workbook.write();
           workbook.close();
           ouputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }   
	}
	
	/**
	 * 20151110  liguowei
	 * 生成文件到指定路径
	 * @param exportDataList
	 * @param filename
	 * @param response
	 * @param path
	 */
	public static void exportDataToPath(List<List<String>> exportDataList,String filename,String path){
		if (exportDataList == null || exportDataList.size() == 0) {
			return;
		}
        try {   
//			filename = new String(filename.getBytes("GBK"), "ISO-8859-1");
        	if(!(new File(path).exists())){
				new File(path).mkdirs();
			}
            WritableWorkbook workbook = Workbook.createWorkbook(new File(path+File.separator+filename));
            WritableSheet sheet = workbook.createSheet("数据导出", 0);
            
        	//设置边框
    		WritableCellFormat wcfTitle = new WritableCellFormat();
    		wcfTitle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
    		//自动换行
    		//wcfTitle.setWrap(true);
    		//设置自动大小
    		CellView cellView = new CellView();
    		cellView.setAutosize(true);
    		sheet.getSettings().setAutomaticFormulaCalculation(true);
    		
            // 表头
    	   if(null != exportDataList && exportDataList.size()!=0){
    		for (int i = 0; i <  exportDataList.get(0).size(); i++) {
    			  sheet.addCell(new Label(i, 0, exportDataList.get(0).get(i),wcfTitle));
			}
    	   }
            //表体
            int row = 1;
            if(null != exportDataList && exportDataList.size()!=0){
            	for (int i = 1; i < exportDataList.size(); i++) {
            		    for (int j = 0; j < exportDataList.get(i).size(); j++) {
            		    	sheet.addCell(new Label(j, row, exportDataList.get(i).get(j)==null?"": exportDataList.get(i).get(j),wcfTitle));
						}
            		    row++;
				}
            }
           workbook.write();
           workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
	}
	/**
	 * 20151114  liguowei
	 * 从指定路径获取文件
	 * @param exportDataList
	 * @param filename
	 * @param response
	 * @param path
	 */
	public static void getExcelToPath(String path,String filename,HttpServletRequest request,HttpServletResponse response){   
		InputStream fis = null ;
		try {
//    			if (StringUtils.contains(request.getHeader("user-agent"), "MSIE")) {
//    				filename = URLEncoder.encode(filename, "utf-8");
//    			} else {
//    				filename = new String(filename.getBytes("utf-8"), "iso-8859-1");
//    			}
		     	response.addHeader("Content-Disposition", "attachment; filename=" +new String(filename.getBytes("GB2312"),"iso-8859-1"));
    			response.setContentType("APPLICATION/VND.MS-EXCEL");
    			//response.setContentType("APPLICATION/OCTET-STREAM");
    			//response.setHeader("Content-Disposition", "attachment; filename=" + filename);
    			
    			 fis = new FileInputStream(new File(path+filename));
    			request.setCharacterEncoding("GB2312");
    			byte[] bytes = new byte[1024];
    			while(fis.read(bytes)!=-1){
    				response.getOutputStream().write(bytes);
    			}
    			response.getOutputStream().flush();
    			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    } 
	
}
