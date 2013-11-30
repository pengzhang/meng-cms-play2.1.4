package models.article;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	public long id;
	
	/**
	 * 文章分类Code
	 */
	@Column
	public String category_code;
	
	/**
	 * 文章分类标题
	 */
	@Column
	public String category_title;
	
	/**
	 * 文章分类的父Code
	 * default:默认分类
	 * channel:频道
	 */
	@Column
	public String parent_category_code = "default";
	
	public static Model.Finder<Long, ArticleCategory> find = new Model.Finder<Long, ArticleCategory>(Long.class, ArticleCategory.class);
	
	/**
	 * 创建文章分类
	 * @param category_title
	 */
	public static void createArticleCategory(ArticleCategory ac){
		ac.category_code = StringUtils.getMengCode();
		ac.save();
	}
	
	/**
	 * 修改文章分类
	 * @param ac
	 */
	public static void modifyArticleCategory(ArticleCategory ac){
		//如果文章分类不存在，则创建一个文章分类
		if(ac.id <0 || getArticleCategoryById(ac.id) ==null){
			createArticleCategory(ac);
		}
		ac.update();
	}
	
	/**
	 * 删除文章分类-ByCode
	 * @param category_code
	 */
	public static void destroyArticleCategoryByCode(String category_code){
		Ebean.delete(find.where().eq("category_code", category_code).findList());
	}
	
	/**
	 * 获取文章分类标题-ByCode
	 * @param category_code
	 * @return
	 */
	public static String getCategoryTitleByCode(String category_code){
		return find.where().eq("category_code", category_code).findUnique().category_title;
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
	 * 根据id获取文章分类
	 * @param id
	 * @return
	 */
	public static ArticleCategory getArticleCategoryById(long id){
		return find.byId(id);
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
	 * 获取全部文章分类
	 * @return
	 */
	public static List<ArticleCategory> getArticleCategoryAll(){
		return find.all();
	}
	
	/**
	 * 分页获取文章分类
	 * @return
	 */
	public static List<ArticleCategory> getArticleCategoryPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	

}
