package models.image;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import play.db.ebean.Model;
import utils.StringUtils;

public class ImageCategory extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	@Column
	public String ic_code;
	
	@Column
	public String category_name;
	
	@Column
	public String category_desc;
	
	@Column
	public String web_site_code;
	
	@Column
	public String parent_category_code = "default";
	
	@Column
	public boolean is_channel = false; 
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_at = new Timestamp(System.currentTimeMillis());
	
	public static Model.Finder<Long, ImageCategory> find = new Model.Finder<Long, ImageCategory>(Long.class, ImageCategory.class);
	
	public static void createImageCategory(ImageCategory ic){
		if(ic.ic_code.equals("default")){
			ic.parent_category_code = "";
		}else{
			ic.ic_code = StringUtils.getMengCode();
		}
		ic.save();
	}
	
	public static void modifyImageCategory(ImageCategory ic){
		ic.id = getImageCategory(ic.ic_code).id;
		ic.update();
	}
	
	public static ImageCategory getImageCategory(String code){
		return find.where().eq("ic_code", code).findUnique();
	}
	
	public static List<ImageCategory> getImageCategoryPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static List<ImageCategory> getWSImageCategory(String ws_code,int page,int size){
		return find.where().eq("web_site_code", ws_code).findPagingList(size).getPage(page).getList();
	}
	
	public static List<ImageCategory> getChildImageCategoryPage(String parent_category_code){
		return find.where().eq("parent_category_code", parent_category_code).findList();
	} 

}
