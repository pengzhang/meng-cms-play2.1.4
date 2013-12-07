package controllers.web.article;

import java.util.List;

import models.article.Article;
import models.assist.ArticleTitle;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import utils.StringUtils;

public class WebArticle extends Controller{
	
	/**
	 * 创建文章
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public static Result create(){
		RequestBody body = request().body();
		Article article  = Json.fromJson(body.asJson(), Article.class);
		String article_code = Article.createArticle(article);
		return ok(StringUtils.returnSuccess(article_code)).as(StringUtils.APPLICATION_JSON);
	}
	
	/**
	 * 通过code获取文章内容
	 * @param code
	 * @return article json
	 */
	public static Result getArticle(String code){
		Article article  = Article.getArticleByCode(code);
		return ok(Json.toJson(article)).as(StringUtils.APPLICATION_JSON);
	}
	
	/**
	 * 获取文章列表标题
	 * @param page
	 * @param size
	 * @param category_code 文章分类编号
	 * @return List<article> json
	 */
	public static Result getArticlePage(String category_code,int page,int size){
		System.out.println(category_code+page+size);
		List<ArticleTitle> ats  = Article.getArticleTitlePageByCategoryCode(category_code, page, size);
		return ok(StringUtils.listJson("articles", ats)).as(StringUtils.APPLICATION_JSON);
	}
	
	

}
