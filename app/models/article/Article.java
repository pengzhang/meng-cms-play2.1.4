package models.article;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import models.assist.ArticleTitle;

import org.springframework.format.annotation.DateTimeFormat;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import utils.StringUtils;

import com.avaje.ebean.Ebean;

/**
 * 文章管理
 * @author zhangpeng
 *
 */
@Entity
@Table(name="article")
public class Article extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文章ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	
	/**
	 * 文章Code
	 */
	@Column
	@Required
	public String article_code;
	
	/**
	 * 文章标题
	 */
	@Column
	@Required
	public String article_title;
	
	/**
	 * 文章内容
	 */
	@Lob
	public String article_content;
	
	/**
	 * 文章作者
	 */
	@Column
	public String article_author;
	
	/**
	 * 文章添加时间
	 */
	@Column
	@Required
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp article_date = new Timestamp(System.currentTimeMillis());
	
	/**
	 * 文章主题
	 */
	@Column
	public String article_subject;
	
	/**
	 * 文章分类
	 * default:默认分类
	 */
	@Column
	public String article_category_code;
	
	/**
	 * 审核状态
	 * 默认:不通过
	 */
	@Column
	public boolean article_auditstatus=false;
	
	public static Model.Finder<Long, Article> find = new Model.Finder<Long, Article>(Long.class, Article.class);
	
	/**
	 * 创建文章
	 * 默认分类：default
	 * @param article
	 * @return article_code
	 */
	public static String createArticle(Article article){
		article.article_code = StringUtils.getMengCode();
		if(article.article_category_code == null || article.article_category_code.equals("")){
			article.article_category_code = "default";
		}
		article.save();
		return article.article_code;
	}
	
	/**
	 * 修改文章
	 * @param article
	 * @return article_code
	 */
	public static String modifyArticle(Article article){
		/**
		 * 如果数据库中不存在,创建新文章
		 * 修改的文章,均需重新审核
		 */
		Article art = getArticleByCode(article.article_code);
		if( art == null ){
			createArticle(article);
		}else{
			article.id  = art.id;
			article.article_auditstatus = false;
			article.update();
		}
		return article.article_code;
	}
	
	/**
	 * 删除文章-ByCode
	 * @param article_code
	 * @return article_category_code 文章分类编号
	 */
	public static String deleteArticle(String article_code){
		Ebean.delete(find.where().eq("article_code", article_code).findList());
		return getArticleByCode(article_code).article_category_code;
	}
	
	/**
	 * 删除文章(批量)
	 * @param article_codes
	 */
	public static void deleteArticle(List<String> article_codes){
		for(String article_code : article_codes){
			deleteArticle(article_code);
		}
	}
	
	/**
	 * 审核文章(批量)
	 * @param article_codes
	 */
	public static void publishArticle(List<String> article_codes){
		for(String article_code : article_codes){
			Article article = getArticleByCode(article_code);
			article.article_auditstatus = true;
			article.update();
		}
	}
	
	/**
	 * 审核文章
	 * @param article_codes
	 * @return article_category_code 文章分类编号
	 */
	public static String publishArticle(String article_code){
		Article article = getArticleByCode(article_code);
		article.article_auditstatus = true;
		article.update();
		return article.article_category_code;
		
	}
	
	/**
	 * 查询文章内容-ByCode
	 * @param article_code
	 * @return
	 */
	public static Article getArticleByCode(String article_code){
		return find.where().eq("article_code", article_code).findUnique();
	}
	
	/**
	 * 查询文章内容
	 * @param article_id
	 * @return
	 */
	public static Article getArticleById(long id){
		return find.byId(id);
	}
	
	/**
	 * 根据文章分类获取文章列表-ByCategoryCode
	 * @param category_code
	 * @return
	 */
	public static List<Article> getArticlesByCategoryCode(String category_code){
		return find.where().eq("article_category_code", category_code).findList();
	}
	
	/**
	 * 根据分类获取文章分页列表-ByCategoryCode and Page
	 * @param category_code
	 * @param page
	 * @param size
	 * @return
	 */
	public static List<Article> getArticlePageByCategoryCode(String category_code, int page, int size){
		List<Article> articles = find.select("article_code,article_title,article_author,article_date,article_subject,article_auditstatus")
								.where().eq("article_category_code", category_code)
								.orderBy().asc("article_auditstatus")
								.findPagingList(size).setFetchAhead(false).getPage(page)
								.getList();
		return articles;
	}
	
	/**
	 * 根据分类获取文章标题分页列表-ByCategoryCode and Page
	 * @param category_code
	 * @param page
	 * @param size
	 * @return
	 */
	public static List<ArticleTitle> getArticleTitlePageByCategoryCode(String category_code, int page, int size){
		List<Article> articles = find.select("article_code,article_title").where().eq("article_category_code", category_code)
								.orderBy().desc("article_date")
								.findPagingList(size).setFetchAhead(false).getPage(page)
								.getList();
		List<ArticleTitle> articleTitles = new ArrayList<ArticleTitle>();
		for(Article article : articles){
			ArticleTitle at = new ArticleTitle();
			at.article_code = article.article_code;
			at.article_title = article.article_title;
			articleTitles.add(at);
		}
		return articleTitles;
	}
	
	/**
	 * 根据分类获取文章Top多少篇
	 * @param category_code
	 * @param top
	 * @return
	 */
	public static List<Article> getArticleTop(String category_code, int top){
		return find.where().eq("article_category_code", category_code).orderBy().desc("article_date").findPagingList(top).getPage(0).getList();
	}
	
}
