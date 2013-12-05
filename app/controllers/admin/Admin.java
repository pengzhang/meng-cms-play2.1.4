package controllers.admin;

import java.util.List;

import models.article.Article;
import models.article.ArticleCategory;
import models.news.News;
import models.news.NewsCategory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;
import views.html.admin.article.*;
import views.html.admin.question.*;
import views.html.admin.message.*;
import views.html.admin.user.*;
import views.html.admin.advertising.*;
import views.html.admin.faq.*;
import views.html.admin.image.*;
import views.html.admin.news.*;
import views.html.admin.download.*;
import views.html.admin.system.*;

public class Admin extends Controller {
	
	public static Result index(){
		return ok(index.render());
	}
	
	public static Result main(){
		return ok(dashboard.render());
	}
	
	public static Result article(String code){
		List<Article> articles = Article.getArticlePageByCategoryCode(code, 0, 10);
		return ok(article_view.render(articles));
	}
	
	public static Result article_category(){
		List<ArticleCategory> acs = ArticleCategory.getArticleCategoryPage(0, 10);
		return ok(article_category_view.render(acs));
	}
	
	public static Result news(String code){
		List<News> news = News.getNewsPageByCategoryCode(code, 0, 10);
		return ok(news_view.render(news));
	}
	
	public static Result news_category(){
		List<NewsCategory> ncs = NewsCategory.getNewsCategoryPage(0, 10);
		return ok(news_category_view.render(ncs));
	}
	
	public static Result message(){
		return ok(message_view.render());
	}
	
	public static Result question(){
		return ok(question_view.render());
	}
	
	public static Result exam(){
		return ok(exam_view.render());
	}
	
	public static Result user(){
		return ok(user_view.render());
	}
	
	public static Result advertising(){
		return ok(advertising_view.render());
	}
	
	public static Result faq(){
		return ok(faq_view.render());
	}
	
	public static Result image(){
		return ok(image_view.render());
	}
	
	public static Result image_category(){
		return ok(image_category_view.render());
	}
	
	public static Result download(){
		return ok(download_view.render());
	}
	
	public static Result download_category(){
		return ok(download_category_view.render());
	}
	
	public static Result system(){
		return ok(system_view.render());
	}

}
