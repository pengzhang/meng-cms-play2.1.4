package models.advertising;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import play.db.ebean.Model;
import utils.StringUtils;

import com.avaje.ebean.Ebean;

/**
 * 广告管理
 * @author zhangpeng
 *
 */
@Entity
@Table(name="advertising")
public class Advertising extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public long id;
	
	/**
	 * 广告Code
	 */
	@Column
	public String ad_code;
	
	/**
	 * 广告位置
	 */
	@Column
	public String ad_position;
	
	/**
	 * 广告脚本
	 */
	@Column
	public String ad_script;
	
	/**
	 * 广告链接URL
	 */
	@Column
	public String ad_url;
	
	/**
	 * 广告图片
	 */
	@Column
	public String ad_image;
	
	/**
	 * 广告文字
	 */
	@Column
	public String ad_text;
	
	/**
	 * 广告类型
	 * IMAGE:图片广告
	 * TEXT:文字广告
	 * IMAGE_TEXT:图文广告
	 * OTHER:广告商广告
	 */
	@Column
	public String ad_type;
	
	/**
	 * 广告上架
	 */
	@Column
	public boolean online=true;
	
	/**
	 * 隶属网站
	 */
	@Column
	public String web_site_code;
	
	/**
	 * 广告到期日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date endate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());
	
	
	public static Model.Finder<Long, Advertising> find = new Model.Finder<Long, Advertising>(Long.class, Advertising.class);
	
	/**
	 * 创建广告
	 * @param ad
	 */
	public static void create_AD(Advertising ad){
		ad.ad_code = StringUtils.getMengCode();
		ad.save();
	}
	
	/**
	 * 修改广告
	 * @param ad
	 */
	public static void modify_AD(Advertising ad){
		ad.id = get_ADByCode(ad.ad_code).id;
		ad.update();
	}
	
	/**
	 * 删除广告
	 * @param ac_code
	 */
	public static void delete_AD(String ad_code){
		Ebean.delete(find.where().eq("ad_code", ad_code).findList());
	}
	
	/**
	 * 根据广告位置获取广告内容
	 * @param ad_position
	 * @return
	 */
	public static Advertising get_AD(String ad_position){
		return find.where().eq("ad_position", ad_position).findUnique();
	}
	
	public static Advertising get_ADByCode(String ad_code){
		return find.where().eq("ad_code", ad_code).findUnique();
	}
	
	public static List<Advertising> getAdvertisingList(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static List<Advertising> getWSAdvertisingList(String ws_code,int page,int size){
		return find.where().eq("web_site_code", ws_code).findPagingList(size).getPage(page).getList();
	}
	
	/**
	 * 广告上架
	 * @param ad_code
	 */
	public static void online_AD(String ad_code){
		Advertising ad = find.where().eq("ad_code", ad_code).findUnique();
		ad.online = true;
		ad.update();
	}
	
	/**
	 * 广告下架
	 * @param ad_code
	 */
	public static void offline_AD(String ad_code){
		Advertising ad = find.where().eq("ad_code", ad_code).findUnique();
		ad.online = false;
		ad.update();
	}

}
