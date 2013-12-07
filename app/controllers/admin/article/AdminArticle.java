package controllers.admin.article;

import java.util.List;
import java.util.Map;

import models.article.Article;
import models.article.ArticleCategory;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.article.article;

public class AdminArticle extends Controller {
	
	public static Result add(){
		List<ArticleCategory> acs = ArticleCategory.getArticleCategoryAll();
		return ok(article.render(acs,null,true));
	}
	
	public static Result edit(String code){
		List<ArticleCategory> acs = ArticleCategory.getArticleCategoryAll();
		Article art  = Article.getArticleByCode(code);
		return ok(article.render(acs,art,false));
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
		return redirect("/admin/article/view/"+article.article_category_code);
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Article article = new Article();
		article.article_code = map.get("article_code");
		article.article_title = map.get("article_title");
		article.article_content = map.get("article_content");
		article.article_author = map.get("article_author");
		article.article_subject = map.get("article_subject");
		article.article_category_code = map.get("article_category_code");
		Article.modifyArticle(article);
		return redirect("/admin/article/view/"+article.article_category_code);
	}
	
	public static Result delete(String article_code){
		String acc = Article.deleteArticle(article_code);
		return redirect("/admin/article/view/"+acc);
	}
	
	public static Result audit(String article_code){
		String acc = Article.publishArticle(article_code);
		return redirect("/admin/article/view/"+acc);
	}

}
