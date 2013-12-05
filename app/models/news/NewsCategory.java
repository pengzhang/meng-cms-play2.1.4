package models.news;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import utils.StringUtils;

import com.avaje.ebean.Ebean;

/**
 * 新闻分类管理
 * @author zhangpeng
 *
 */

@Entity
@Table(name="news_category")
public class NewsCategory extends Model{
	
	/**
	 * 新闻分类的 serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 新闻分类ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	
	/**
	 * 新闻分类Code
	 */
	@Column
	@Required
	public String category_code;
	
	/**
	 * 新闻分类标题
	 */
	@Column
	@Required
	public String category_title;
	
	/**
	 * 新闻分类的父Code
	 * default:默认分类
	 * channel:频道
	 */
	@Column
	public String parent_category_code = "default";
	
	@Column
	public boolean is_channel = false; 
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_at = new Timestamp(System.currentTimeMillis());
	
	public static Model.Finder<Long, NewsCategory> find = new Model.Finder<Long, NewsCategory>(Long.class, NewsCategory.class);
	
	/**
	 * 创建新闻分类
	 * @param category_title
	 * @return category_code 新闻分类编号
	 */
	public static String createNewsCategory(NewsCategory ac){
		ac.category_code = StringUtils.getMengCode();
		ac.save();
		return ac.category_code;
	}
	
	/**
	 * 修改新闻分类
	 * @param ac
	 */
	public static void modifyNewsCategory(NewsCategory ac){
		//如果新闻分类不存在，则创建一个新闻分类
		NewsCategory category = NewsCategory.getNewsCategoryByCode(ac.category_code);
		if(ac.id <0 || category ==null){
			createNewsCategory(ac);
		}else{
			ac.id = category.id;
			ac.update();
		}
	}
	
	/**
	 * 删除新闻分类-ByCode
	 * @param category_code
	 */
	public static void destroyNewsCategoryByCode(String category_code){
		Ebean.delete(find.where().eq("category_code", category_code).findList());
	}
	
	/**
	 * 获取新闻分类标题-ByCode
	 * @param category_code
	 * @return
	 */
	public static String getCategoryTitleByCode(String category_code){
		NewsCategory ac = find.where().eq("category_code", category_code).findUnique();
		if(ac == null){
			return "顶级分类";
		}
		return ac.category_title;
	}
	
	/**
	 * 根据code获取新闻分类
	 * @param category_code
	 * @return
	 */
	public static NewsCategory getNewsCategoryByCode(String category_code){
		return find.where().eq("category_code", category_code).findUnique();
	}
	
	/**
	 * 根据id获取新闻分类
	 * @param id
	 * @return
	 */
	public static NewsCategory getNewsCategoryById(long id){
		return find.byId(id);
	}
	
	/**
	 * 获取新闻分类的子节点_ByParentCategoryCode
	 * @param parent_category_code
	 * @return
	 */
	public static List<NewsCategory> getChildCategoryByCode(String parent_category_code){
		return find.where().eq("parent_category_code", parent_category_code).findList();
	}
	
	/**
	 * 获取全部新闻分类
	 * @return
	 */
	public static List<NewsCategory> getNewsCategoryAll(){
		return find.all();
	}
	
	/**
	 * 分页获取新闻分类
	 * @return
	 */
	public static List<NewsCategory> getNewsCategoryPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	

}
