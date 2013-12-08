package models.download;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;
import utils.StringUtils;


public class DownloadCategory  extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	@Column
	public String dc_code = StringUtils.getMengCode();
	@Column
	public String dc_name;
	@Column
	public String dc_desc;
	@Column
	public String parent_dc_code;
	@Column
	public String web_site_code;
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());;
	
	public static Model.Finder<Long, DownloadCategory> find = new Model.Finder<Long, DownloadCategory>(Long.class, DownloadCategory.class);
	
	public static void createDownCategory(DownloadCategory dc){
		dc.save();
	}
	
	public static void modifyDownCategory(DownloadCategory dc){
		dc.id = getDownCategory(dc.dc_code).id;
		dc.update();
	}
	
	public static void deleteDownCategory(String code){
		Ebean.delete(find.where().eq("dc_code", code).findList());
	}
	
	public static DownloadCategory getDownCategory(String code){
		return find.where().eq("dc_code", code).findUnique();
	}
	
	public static List<DownloadCategory> getDownCategoryPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static List<DownloadCategory> getWSDownCategoryPage(String ws_code,int page,int size){
		return find.where().eq("web_site_code", ws_code).findPagingList(size).getPage(page).getList();
	}

	public static List<DownloadCategory> getDownCategoryAll() {
		return find.all();
	}
	
}
