package models.news;

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

import org.springframework.format.annotation.DateTimeFormat;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import utils.StringUtils;

import com.avaje.ebean.Ebean;

/**
 * 新闻管理
 * @author zhangpeng
 *
 */
@Entity
@Table(name="news")
public class News extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 新闻ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
	
	/**
	 * 新闻Code
	 */
	@Column
	@Required
	public String news_code;
	
	/**
	 * 新闻标题
	 */
	@Column
	@Required
	public String news_title;
	
	/**
	 * 新闻内容
	 */
	@Lob
	public String news_content;
	
	/**
	 * 新闻作者
	 */
	@Column
	public String news_author;
	
	/**
	 * 新闻添加时间
	 */
	@Column
	@Required
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp news_date = new Timestamp(System.currentTimeMillis());
	
	/**
	 * 新闻主题
	 */
	@Column
	public String news_subject;
	
	/**
	 * 新闻分类
	 * default:默认分类
	 */
	@Column
	public String news_category_code;
	
	/**
	 * 审核状态
	 * 默认:不通过
	 */
	@Column
	public boolean news_auditstatus=false;
	
	public static Model.Finder<Long, News> find = new Model.Finder<Long, News>(Long.class, News.class);
	
	/**
	 * 创建新闻
	 * 默认分类：default
	 * @param news
	 * @return news_code
	 */
	public static String createNews(News news){
		news.news_code = StringUtils.getMengCode();
		if(news.news_category_code == null || news.news_category_code.equals("")){
			news.news_category_code = "default";
		}
		news.save();
		return news.news_code;
	}
	
	/**
	 * 修改新闻
	 * @param news
	 * @return news_code
	 */
	public static String modifyNews(News news){
		/**
		 * 如果数据库中不存在,创建新新闻
		 * 修改的新闻,均需重新审核
		 */
		News art = getNewsByCode(news.news_code);
		if( art == null ){
			createNews(news);
		}else{
			news.id  = art.id;
			news.news_auditstatus = false;
			news.update();
		}
		return news.news_code;
	}
	
	/**
	 * 删除新闻-ByCode
	 * @param news_code
	 * @return news_category_code 新闻分类编号
	 */
	public static String destroyNews(String news_code){
		Ebean.delete(find.where().eq("news_code", news_code).findList());
		return getNewsByCode(news_code).news_category_code;
	}
	
	/**
	 * 删除新闻(批量)
	 * @param news_codes
	 */
	public static void destoryNews(List<String> news_codes){
		for(String news_code : news_codes){
			destroyNews(news_code);
		}
	}
	
	/**
	 * 审核新闻(批量)
	 * @param news_codes
	 */
	public static void publishNews(List<String> news_codes){
		for(String news_code : news_codes){
			News news = getNewsByCode(news_code);
			news.news_auditstatus = true;
			news.update();
		}
	}
	
	/**
	 * 审核新闻
	 * @param news_codes
	 * @return news_category_code 新闻分类编号
	 */
	public static String publishNews(String news_code){
		News news = getNewsByCode(news_code);
		news.news_auditstatus = true;
		news.update();
		return news.news_category_code;
		
	}
	
	/**
	 * 查询新闻内容-ByCode
	 * @param news_code
	 * @return
	 */
	public static News getNewsByCode(String news_code){
		return find.where().eq("news_code", news_code).findUnique();
	}
	
	/**
	 * 查询新闻内容
	 * @param news_id
	 * @return
	 */
	public static News getNewsById(long id){
		return find.byId(id);
	}
	
	/**
	 * 根据新闻分类获取新闻列表-ByCategoryCode
	 * @param category_code
	 * @return
	 */
	public static List<News> getNewssByCategoryCode(String category_code){
		return find.where().eq("news_category_code", category_code).findList();
	}
	
	/**
	 * 根据分类获取新闻分页列表-ByCategoryCode and Page
	 * @param category_code
	 * @param page
	 * @param size
	 * @return
	 */
	public static List<News> getNewsPageByCategoryCode(String category_code, int page, int size){
		List<News> newss = find.select("news_code,news_title,news_author,news_date,news_subject,news_auditstatus")
								.where().eq("news_category_code", category_code)
								.orderBy().asc("news_auditstatus")
								.findPagingList(size).setFetchAhead(false).getPage(page)
								.getList();
		return newss;
	}
	
	/**
	 * 根据分类获取新闻标题分页列表-ByCategoryCode and Page
	 * @param category_code
	 * @param page
	 * @param size
	 * @return
	 */
	public static List<NewsTitle> getNewsTitlePageByCategoryCode(String category_code, int page, int size){
		List<News> newss = find.select("news_code,news_title").where().eq("news_category_code", category_code)
								.orderBy().desc("news_date")
								.findPagingList(size).setFetchAhead(false).getPage(page)
								.getList();
		List<NewsTitle> newsTitles = new ArrayList<NewsTitle>();
		for(News news : newss){
			NewsTitle nt = new NewsTitle();
			nt.news_code = news.news_code;
			nt.news_title = news.news_title;
			newsTitles.add(nt);
		}
		return newsTitles;
	}
	
	/**
	 * 根据分类获取新闻Top多少篇
	 * @param category_code
	 * @param top
	 * @return
	 */
	public static List<News> getNewsTop(String category_code, int top){
		return find.where().eq("news_category_code", category_code).orderBy().desc("news_date").findPagingList(top).getPage(0).getList();
	}
	
}
