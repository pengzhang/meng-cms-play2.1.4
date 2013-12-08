package models.service;

import java.util.List;

import models.news.News;
import models.news.NewsCategory;
import play.db.ebean.Model;

/**
 * 新闻频道管理
 * @author zhangpeng
 *
 */
public class NewsChannel {
	
	/**
	 * 新闻Find
	 */
	public static Model.Finder<Long, News> a_find = new Model.Finder<Long, News>(Long.class, News.class);
	/**
	 * 新闻分类Find
	 */
	public static Model.Finder<Long, NewsCategory> ac_find = new Model.Finder<Long, NewsCategory>(Long.class, NewsCategory.class);
	
	
	/**
	 * 获取顶级新闻列表(频道)
	 * @return
	 */
	public static List<NewsCategory> getChannels(){
		return ac_find.where().eq("parent_category_code", "channel").findList();
	}
	
	/**
	 * 获取该频道下的新闻
	 * @param category_code
	 * @param page
	 * @param size
	 * @return
	 */
	public static List<News> getNewsByChannel(String category_code, int page, int size){
		return News.getNewsPageByCategoryCode(category_code, page, size);
	}
}
