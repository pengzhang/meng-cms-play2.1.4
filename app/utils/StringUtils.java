package utils;

import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 字符串工具类
 * @author zhangpeng
 *
 */
public class StringUtils {
	
	public static String getForm(Map<String,String> map,String str){
		return map.get(str);
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
