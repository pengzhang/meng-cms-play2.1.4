package controllers.admin.article;

import java.util.List;
import java.util.Map;

import models.article.ArticleCategory;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.article.article_category;

public class AdminArticleCategory extends Controller {
	
	public static Result add(){
		List<ArticleCategory> acs = ArticleCategory.getArticleCategoryAll();
		return ok(article_category.render(acs,null,true));
	}
	
	public static Result edit(String code){
		List<ArticleCategory> acs = ArticleCategory.getArticleCategoryAll();
		ArticleCategory ac = ArticleCategory.getArticleCategoryByCode(code);
		return ok(article_category.render(acs,ac,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		ArticleCategory ac = new ArticleCategory();
		ac.category_title = map.get("category_title");
		String is_channel = map.get("is_channel");
		ac.parent_category_code = map.get("parent_category_code");
		System.out.println(ac.parent_category_code);
		if(is_channel!=null && is_channel.equals("on")){
			ac.is_channel = true;
		}
		ArticleCategory.createArticleCategory(ac);
		
		return redirect("/admin/article/category");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		ArticleCategory ac = new ArticleCategory();
		ac.category_code = map.get("category_code");
		ac.category_title = map.get("category_title");
		String is_channel = map.get("is_channel");
		ac.parent_category_code = map.get("parent_category_code");
		if(is_channel!=null && is_channel.equals("on")){
			ac.is_channel = true;
		}
		ArticleCategory.modifyArticleCategory(ac);
		return redirect("/admin/article/category");
	}
	
	public static Result delete(String code){
		ArticleCategory.destroyArticleCategoryByCode(code);
		return redirect("/admin/article/category");
	}

}
