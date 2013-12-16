package utils;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import play.libs.Json;

/**
 * 字符串工具类
 * @author zhangpeng
 *
 */
public class StringUtils {
	
	public final static String APPLICATION_JSON = "application/json";
	
	public final static String TEXT_PLAIN = "text/plain";
	
	public static String returnSuccess(){
		return "{\"status\":\"success\"}";
	}
	public static String returnSuccess(String field){
		return "{\"status\":\"success\",\"code\":\""+field+"\"}";
	}
	
	public static String listJson(String field,Object obj){
		return "{\""+field+"\":"+Json.toJson(obj)+"}";
	}
	
	public static String getForm(Map<String,String> map,String str){
		return map.get(str);
	}
	
	public static String getDatePath(String path){
		Calendar calendar = GregorianCalendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String date_path = File.separator+year+File.separator+month+File.separator+day+File.separator;
		if(path==null || path.equals("")){
			return date_path;
		}
		return File.separator+path+date_path;
	}
	
	public static java.sql.Date convertUtiltoSqlDate(String date_str,String pattern){
		try {
			return new java.sql.Date(DateUtils.parseDate(date_str ,pattern).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	public static String md5(String str){
		return DigestUtils.md5Hex(str);
	}
	
	/**
	 * 获得用户激活码
	 * @return
	 */
	public static String get_verifycode(){
		return RandomStringUtils.randomAlphabetic(10);
	}
	
	/**
	 * 获得access_token
	 * @return
	 */
	public static String access_token(){
		return RandomStringUtils.randomAlphanumeric(20);
	}
	
	/**
	 * Meng-CMS专用系统唯一标识码
	 * @return
	 */
	public static String getMengCode(){
		//TODO根据此码查询系统信息
		return RandomStringUtils.randomNumeric(32);
	}
	
	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static long getTimeStamp(){
		return new Date().getTime();
	}
	
	/**
	 * 获取标准时间
	 * @return
	 */
	public static String getStanderDate(){
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss");
	}
	
	public static void main(String[] args){
		System.out.println(getStanderDate());
	}
	
}
