package models.article;

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
 * 文章分类管理
 * @author zhangpeng
 *
 */

@Entity
@Table(name="article_category")
public class ArticleCategory extends Model{
	
	/**
	 * 文章分类的 serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文章分类ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	
	/**
	 * 文章分类Code
	 */
	@Column
	@Required
	public String category_code;
	
	/**
	 * 文章分类标题
	 */
	@Column
	@Required
	public String category_title;
	
	/**
	 * 文章分类的父Code
	 * default:默认分类
	 * channel:频道
	 */
	@Column
	public String parent_category_code = "default";
	
	@Column
	public String web_site_code;
	
	@Column
	public boolean is_channel = false; 
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_at = new Timestamp(System.currentTimeMillis());
	
	public static Model.Finder<Long, ArticleCategory> find = new Model.Finder<Long, ArticleCategory>(Long.class, ArticleCategory.class);
	
	/**
	 * 创建文章分类
	 * @param category_title
	 * @return category_code 文章分类编号
	 */
	public static String createArticleCategory(ArticleCategory ac){
		if(ac.category_code.equals("default")){
			ac.parent_category_code = "";
		}else{
			ac.category_code = StringUtils.getMengCode();
		}
		ac.save();
		return ac.category_code;
	}
	
	/**
	 * 修改文章分类
	 * @param ac
	 */
	public static void modifyArticleCategory(ArticleCategory ac){
		//如果文章分类不存在，则创建一个文章分类
		ArticleCategory category = ArticleCategory.getArticleCategoryByCode(ac.category_code);
		if(ac.id <0 || category ==null){
			createArticleCategory(ac);
		}else{
			ac.id = category.id;
			ac.update();
		}
	}
	
	/**
	 * 删除文章分类-ByCode
	 * @param category_code
	 */
	public static void deleteArticleCategoryByCode(String category_code){
		Ebean.delete(find.where().eq("category_code", category_code).findList());
	}
	
	/**
	 * 获取文章分类标题-ByCode
	 * @param category_code
	 * @return
	 */
	public static String getCategoryTitleByCode(String category_code){
		ArticleCategory ac = find.where().eq("category_code", category_code).findUnique();
		if(ac == null){
			return "顶级分类";
		}
		return ac.category_title;
	}
	
	/**
	 * 根据code获取文章分类
	 * @param category_code
	 * @return
	 */
	public static ArticleCategory getArticleCategoryByCode(String category_code){
		return find.where().eq("category_code", category_code).findUnique();
	}
	
	/**
	 * 获取文章分类的子节点_ByParentCategoryCode
	 * @param parent_category_code
	 * @return
	 */
	public static List<ArticleCategory> getChildCategoryByCode(String parent_category_code){
		return find.where().eq("parent_category_code", parent_category_code).findList();
	}
	
	/**
	 * 分页获取文章分类
	 * @return
	 */
	public static List<ArticleCategory> getArticleCategoryPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	/**
	 * 分页获取文章分类
	 * @return
	 */
	public static List<ArticleCategory> getWSArticleCategoryPage(String ws_code, int page,int size){
		return find.where().eq("web_site_code", ws_code).findPagingList(size).getPage(page).getList();
	}
	

}
