package models.image;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.FileUtils;
import org.springframework.format.annotation.DateTimeFormat;

import play.Play;
import play.db.ebean.Model;
import utils.StringUtils;

import com.avaje.ebean.Ebean;

/**
 * 图片管理
 * @author zhangpeng
 *
 */
@Entity
@Table(name="image")
public class Image extends Model{

	private static final long serialVersionUID = 1L;
	@Id
	public long id;
	/**
	 * 图片Code
	 */
	@Column
	public String img_code;
	
	@Column
	public String image_name;
	
	@Column
	public String image_desc;
	
	/**
	 * 图片URL
	 * 网络图片
	 */
	@Column
	public String image_url;
	/**
	 * 图片的字节码
	 * 用于存储上传图片
	 */
	@Transient
	public File image_file;
	@Column
	public String image_file_name;
	/**
	 * 图片存放的路径
	 * 用于存储上传图片的服务器路径
	 */
	@Column
	public String image_path;
	
	/**
	 * 图片类型
	 * URL:网络图片
	 * UPLOAD:自行上传图片
	 */
	@Column
	public String image_type;
	
	/**
	 * 所属图片分类的Code
	 */
	@Column
	public String category_code;
	
	/**
	 * 所属文章的Code
	 */
	@Column
	public String article_code;
	
	@Column
	public boolean image_status = false;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());
	
	public static Model.Finder<Long, Image> find = new Model.Finder<Long, Image>(Long.class, Image.class);
	
	/**
	 * 创建图片
	 * @param img
	 */
	public static void createImage(Image img){
		img.img_code = StringUtils.getMengCode();
		img.image_path = Play.application().path()+ "/public/upload/images" + StringUtils.getDatePath(""); 
		if(img.image_type.equalsIgnoreCase("upload")){
			File file = img.image_file;
			File descFile = new File(img.image_path+File.separator+img.image_file_name);
			try {
				FileUtils.copyFile(file,descFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		img.image_path = "upload/images" + StringUtils.getDatePath("");
		img.save();
	}
	
	/**
	 * 修改图片
	 * @param img
	 */
	public static void modifyImage(Image img){
		img.id = getImage(img.img_code).id;
		if(img.image_type.equalsIgnoreCase("upload")){
			File file = img.image_file;
			File descFile = new File(img.image_path);
			try {
				FileUtils.copyFile(file,descFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		img.image_status = false;
		img.update();
	}
	
	/**
	 * 删除图片
	 * @param img_code
	 */
	public static void deleteImage(String img_code){
		//TODO 删除图片文件
		Ebean.delete(find.where().eq("img_code", img_code).findList());
	}
	
	/**
	 * 获取图片-ByCode
	 * @param img_code
	 * @return
	 */
	public static Image getImage(String img_code){
		return find.where().eq("img_code", img_code).findUnique();
	}
	
	public static List<Image> getImagePage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static List<Image> getImagePageByCategory(String category_code,int page,int size){
		return find.where().eq("category_code", category_code).findPagingList(size).getPage(page).getList();
	}
	
	public static List<Image> getImagePageByArticle(String article_code,int page,int size){
		return find.where().eq("article_code", article_code).findPagingList(size).getPage(page).getList();
	}

	public static void auditImage(String code) {
		Image img = getImage(code);
		img.image_status = true;
		img.update();
	}
}
