package controllers.admin.article;

import models.article.Article;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.RequestBody;
import utils.StringUtils;
import views.html.admin.article.*;

public class AdminArticle extends Controller {
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result edit(){
		RequestBody body = request().body();
		Article article  = Json.fromJson(body.asJson(), Article.class);
		String article_code = Article.modifyArticle(article);
		return ok(StringUtils.returnSuccess(article_code)).as(StringUtils.APPLICATION_JSON);
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result delete(){
		RequestBody body = request().body();
		Article article  = Json.fromJson(body.asJson(), Article.class);
		String article_code = Article.modifyArticle(article);
		return ok(StringUtils.returnSuccess(article_code)).as(StringUtils.APPLICATION_JSON);
	}
	
	public static Result add(){
		return ok(article.render());
	}
	
	public static Result edit(String code){
		return ok(article.render());
	}
	
	public static Result create(){
		return redirect("/admin/article");
	}
	
	public static Result update(){
		return redirect("/admin/article");
	}
	
	public static Result delete(String code){
		return redirect("/admin/article");
	}

}
