package models.download;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

import play.db.ebean.Model;
import utils.StringUtils;

import com.avaje.ebean.Ebean;


public class Download extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	@Column
	public String soft_code = StringUtils.getMengCode();
	@Column
	public String soft_name;
	@Lob
	public String soft_desc;
	@Column
	public String soft_size;
	@Column
	public String soft_lang; // 英文,中文
	@Column
	public String soft_sys; //windows,linux
	@Column
	public String soft_ver; //版本
	@Column
	public String soft_type;//免费,收费
	@Column
	public String soft_url;
	@Column
	public boolean soft_status = false; //审核通过
	@Column
	public String down_category_code;
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date update_date;
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());
	
	public static Model.Finder<Long, Download> find = new Model.Finder<Long, Download>(Long.class, Download.class);
	
	public static void createDownload(Download dl){
		dl.save();
	}
	
	public static void modifyDownload(Download dl){
		dl.id = getDownload(dl.soft_code).id;
		dl.soft_status = false;
		dl.update();
	}
	
	public static void deleteDownload(String code){
		Ebean.delete(find.where().eq("soft_code", code).findList());
	}
	
	public static Download getDownload(String code){
		return find.where().eq("soft_code",code).findUnique();
	}
	
	public static List<Download> getDownloadPage(int page, int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static List<Download> getDCDownloadPage(String dc_code, int page, int size){
		return find.where().eq("down_category_code", dc_code).findPagingList(size).getPage(page).getList();
	}

}
