package models.adverties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	 * 广告到期日期
	 */
	@Column
	public String endate;
	
	
	public Advertising() {
		super();
	}

	public Advertising(String ad_code, String ad_position, String ad_script,
			String ad_url, String ad_image, String ad_text, String ad_type,
			boolean online, String endate) {
		super();
		this.ad_code = ad_code;
		this.ad_position = ad_position;
		this.ad_script = ad_script;
		this.ad_url = ad_url;
		this.ad_image = ad_image;
		this.ad_text = ad_text;
		this.ad_type = ad_type;
		this.online = online;
		this.endate = endate;
	}

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
		ad.update();
	}
	
	/**
	 * 删除广告
	 * @param ac_code
	 */
	public static void destroy_AD(String ad_code){
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
