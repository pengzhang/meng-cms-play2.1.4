package models.download;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;
import utils.StringUtils;

@Entity
@Table(name="download_url")
public class DownloadUrl extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	@Column
	public String soft_code;
	@Column
	public String dl_url_code = StringUtils.getMengCode();
	@Column
	public String soft_down_site; //多特,天空
	@Column
	public String soft_url;
	
	public static Model.Finder<Long, DownloadUrl> find = new Model.Finder<Long, DownloadUrl>(Long.class, DownloadUrl.class);
	
	public static void createDownUrl(DownloadUrl dl_url){
		dl_url.save();
	}
	
	public static void modifyDownUrl(DownloadUrl dl_url){
		dl_url.id = getDownloadUrl(dl_url.dl_url_code).id;
		dl_url.update();
	}
	
	public static void deleteDownUrl(String code){
		Ebean.delete(find.where().eq("dl_url_code", code).findList());
	}
	
	public static void deleteSoftDownUrl(String soft_code){
		Ebean.delete(find.where().eq("soft_code", soft_code).findList());
	}
	
	public static DownloadUrl getDownloadUrl(String code){
		return find.where().eq("dl_url_code", code).findUnique();
	}
	
	public static List<DownloadUrl> getDownloadPage(int page, int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static List<DownloadUrl> getSoftDownloadPage(String soft_code ,int page, int size){
		return find.where().eq("soft_code", soft_code).findPagingList(size).getPage(page).getList();
	}

}
