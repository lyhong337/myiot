package com.chinaunicom.myiot.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileUtils {
	
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
	
    public static boolean createTXTFile(String filePath, String str) {
    	
    	try {
			File filedir = new File(filePath);
			if (!filedir.exists()) { //目录不存在，生成目录
				filedir.mkdirs();
			}
			
			filePath = filePath.replace("\\", "/");
			if (!filePath.endsWith("/")) {
				filePath += "/";
			}
			
			String fileDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); //文件名时间戳
			
			File file = new File(filePath + "result_" + fileDate + ".txt");
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(str);
			bw.flush();
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
    }
    
    /*public static boolean createCSVFile(String filePath, List<ResultDto> resultDtoList) {
    	
    	try {
    		
    		String str = "工号,姓名,考勤日期,打卡时间,日报时间,备注"+"\r\n";
    		for(ResultDto resultDto : resultDtoList) {
    			
    			String userid = resultDto.getUserid();
    			String username = resultDto.getUsername();
    			String kaoqingtime = resultDto.getKaoqingtime(); //考勤时间
    			String dakatime = resultDto.getDakatime();  //打卡时间
    			String rbaotime = resultDto.getRbaotime(); //日报时间
    			boolean flag = resultDto.isFlag();
    			String result = resultDto.getResult();  //打卡结果
    			
    			if(!flag) {
    				str += userid +","+ username +","+ kaoqingtime +","+ dakatime +","+ rbaotime +","+ result +"\r\n";
    			}
				
    		}
    		
			File filedir = new File(filePath);
			if (!filedir.exists()) { //目录不存在，生成目录
				filedir.mkdirs();
			}
			
			filePath = filePath.replace("\\", "/");
			if (!filePath.endsWith("/")) {
				filePath += "/";
			}
			
			String fileDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); //文件名时间戳
			
			File file = new File(filePath + "result_" + fileDate + ".csv");
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(str);
			bw.flush();
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
		return true;
    }*/
    
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
