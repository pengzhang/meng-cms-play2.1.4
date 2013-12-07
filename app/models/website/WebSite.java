package models.website;

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

@Entity
@Table(name="website")
public class WebSite extends Model {

	private static final long serialVersionUID = 1L;
	@Id
	public long id;
	@Column
	public String web_code = StringUtils.getMengCode();
	@Column
	public String web_name;
	@Column
	public String web_url;
	@Column
	public String web_desc;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date;
	
	public static Model.Finder<Long, WebSite> find = new Model.Finder<Long, WebSite>(Long.class, WebSite.class);
	
	public static void createWebSite(WebSite ws){
		ws.save();
	}
	
	public static WebSite getWebSite(String code){
		return find.where().eq("web_code", code).findUnique();
	}
	
	public static void modifyWebSite(WebSite ws){
		ws.id = getWebSite(ws.web_code).id;
		ws.update();
	}
	
	public static void deleteWebSite(String code){
		Ebean.delete(find.where().eq("web_code", code).findList());
	}
	
	public static List<WebSite> getWebSitePage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}

}
