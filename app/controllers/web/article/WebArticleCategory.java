package controllers.web.article;

import java.util.List;

import models.article.ArticleCategory;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.RequestBody;
import utils.StringUtils;

public class WebArticleCategory extends Controller {
	
	/**
	 * 创建文章分类
	 * @return
	 */
	@BodyParser.Of(BodyParser.Json.class)
	public static Result create(){
		RequestBody body = request().body();
		ArticleCategory ac  = Json.fromJson(body.asJson(), ArticleCategory.class);
		String category_code = ArticleCategory.createArticleCategory(ac);
		return ok(StringUtils.returnSuccess(category_code)).as(StringUtils.APPLICATION_JSON);
	}
	
	/**
	 * 获取文章分类的子分类
	 * @param code
	 * @return
	 */
	public static Result getArticleCategoryChild(String code){
		List<ArticleCategory> lacs= ArticleCategory.getChildCategoryByCode(code);
		return ok(StringUtils.listJson("article_categories", lacs)).as(StringUtils.APPLICATION_JSON);
	}
	
	/**
	 * 获取文章分类
	 * @param code
	 * @return
	 */
	public static Result getArticleCategory(String code){
		ArticleCategory category = ArticleCategory.getArticleCategoryByCode(code);
		return ok(Json.toJson(category)).as(StringUtils.APPLICATION_JSON);
	}
	
	/**
	 * 分页获取文章分类
	 * @param page
	 * @param size
	 * @return
	 */
	public static Result getArticleCategoryPage(int page,int size){
		List<ArticleCategory> lacs = ArticleCategory.getArticleCategoryPage(page, size);
		return ok(StringUtils.listJson("article_categories", lacs)).as(StringUtils.APPLICATION_JSON);
	}

}
