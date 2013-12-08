package controllers.admin.news;

import java.util.List;
import java.util.Map;

import models.news.News;
import models.news.NewsCategory;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.news.*;

public class AdminNews extends Controller {
	
	public static Result add(){
		List<NewsCategory> ncs = NewsCategory.getNewsCategoryAll();
		return ok(news.render(ncs,null,true));
	}
	
	public static Result edit(String code){
		List<NewsCategory> ncs = NewsCategory.getNewsCategoryAll();
		News nrt  = News.getNewsByCode(code);
		return ok(news.render(ncs,nrt,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		News news = new News();
		news.news_title = map.get("news_title");
		news.news_content = map.get("news_content");
		news.news_author = map.get("news_author");
		news.news_subject = map.get("news_subject");
		news.news_category_code = map.get("news_category_code");
		News.createNews(news);
		return redirect("/admin/news/view/"+news.news_category_code);
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		News news = new News();
		news.news_code = map.get("news_code");
		news.news_title = map.get("news_title");
		news.news_content = map.get("news_content");
		news.news_author = map.get("news_author");
		news.news_subject = map.get("news_subject");
		news.news_category_code = map.get("news_category_code");
		News.modifyNews(news);
		return redirect("/admin/news/view/"+news.news_category_code);
	}
	
	public static Result delete(String news_code){
		String ncc = News.deleteNews(news_code);
		return redirect("/admin/news/view/"+ncc);
	}
	
	public static Result audit(String news_code){
		String ncc = News.publishNews(news_code);
		return redirect("/admin/news/view/"+ncc);
	}

}
