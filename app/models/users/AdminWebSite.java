package models.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import utils.StringUtils;

@Entity
@Table(name="admin_website")
public class AdminWebSite extends Model {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	@Column
	public String aw_code = StringUtils.getMengCode();
	
	@Column
	public String admin_code;
	
	@Column
	public String web_site_code;
	
	public static Model.Finder<Long, AdminWebSite> find = new Model.Finder<Long, AdminWebSite>(Long.class, AdminWebSite.class);
	
	public static void createAdminWebSite(AdminWebSite aws){
		aws.save();
	}
	
	public static void modifyAdminWebSite(AdminWebSite aws){
		aws.id = getAdminWebSite(aws.aw_code).id;
		aws.update();
	}
	
	public static AdminWebSite getAdminWebSite(String code){
		return find.where().eq("aw_code", code).findUnique();
	}
	
	public static List<AdminWebSite> getAdminWebSitePage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static List<AdminWebSite> getACAdminWebSite(String code){
		return find.where().eq("admin_code", code).findList();
	}

}
