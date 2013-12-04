package controllers.web.article;

import models.article.ArticleCategory;
import play.mvc.Controller;
import play.mvc.Result;

public class WebArticleCategory extends Controller {
	
	public static Result getParentArticleCategoryTitle(String code){
		return ok(ArticleCategory.getCategoryTitleByCode(code));
	}

}
