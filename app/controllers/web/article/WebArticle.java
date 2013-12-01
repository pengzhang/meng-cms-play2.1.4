package controllers.web.article;

import models.article.Article;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import utils.StringUtils;

public class WebArticle extends Controller{
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result create(){
		RequestBody body = request().body();
		Article article  = Json.fromJson(body.asJson(), Article.class);
		String article_code = Article.createArticle(article);
		return ok(StringUtils.returnSuccess(article_code)).as(StringUtils.APPLICATION_JSON);
	}

}
