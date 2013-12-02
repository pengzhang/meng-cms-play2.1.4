package controllers.admin.article;

import java.util.Map;

import models.article.Article;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.article.article;

public class AdminArticle extends Controller {
	
	public static Result add(){
		//TODO 添加文章分类
		return ok(article.render());
	}
	
	public static Result edit(String code){
		
		return ok(article.render());
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Article article = new Article();
		article.article_title = map.get("article_title");
		article.article_content = map.get("article_content");
		article.article_author = map.get("article_author");
		article.article_subject = map.get("article_subject");
		article.article_category_code = map.get("article_category_code");
		Article.createArticle(article);
		return redirect("/admin/article");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Article article = new Article();
		article.article_title = map.get("article_title");
		article.article_content = map.get("article_content");
		article.article_author = map.get("article_author");
		article.article_subject = map.get("article_subject");
		article.article_category_code = map.get("article_category_code");
		Article.modifyArticle(article);
		return redirect("/admin/article");
	}
	
	public static Result delete(String article_code){
		Article.destroyArticle(article_code);
		return redirect("/admin/article");
	}
	
	public static Result audit(String article_code){
		Article.publishArticle(article_code);
		return redirect("/admin/article");
	}

}
