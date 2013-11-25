package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.Logger;
import play.db.ebean.Model;

/**
 * 网站基本信息管理 主站信息 
 * TODO 子站信息管理
 * 
 * @author zhangpeng
 * 
 */
@Entity
@Table(name = "website")
public class WebSite extends Model {

	/**
	 * 网站基本信息serialVersion
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public long id;

	/**
	 * 网站信息Code
	 */
	@Column
	public String website_code = "website";

	/**
	 * 网站名称
	 */
	@Column
	public String website = "Meng-CMS";

	/**
	 * 网站域名
	 */
	@Column
	public String domain = "localhost";
	
	public WebSite() {
		super();
	}

	public WebSite(String website_code, String website, String domain) {
		super();
		this.website_code = website_code;
		this.website = website;
		this.domain = domain;
	}

	public static Model.Finder<Long, WebSite> find = new Model.Finder<Long, WebSite>(
			Long.class, WebSite.class);

	public static void saveWebSite(WebSite ws) {
		if (getWebSite(ws.website_code) != null) {
			Logger.error("add website info error! website info exist.");
		}else{
		ws.save();
		}
	}

	public static void modifyWebSite(WebSite ws) {
		if (getWebSite(ws.website_code) == null) {
			Logger.error("update website info error! website info no exist.");
		} else {
			ws.update();
		}
	}

	public static WebSite getWebSite(String wc) {
		return find.where().eq("website_code", wc).findUnique();
	}

}
