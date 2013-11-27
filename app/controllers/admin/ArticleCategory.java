package controllers.admin;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;

public class ArticleCategory extends Controller {
	
	public static Result add(){
		return ok(article_category.render());
	}
	
	public static Result edit(String code){
		return ok(article_category.render());
	}
	
	public static Result create(){
		return redirect("/admin/article/category");
	}
	
	public static Result update(){
		return redirect("/admin/article/category");
	}
	
	public static Result delete(String code){
		return redirect("/admin/article/category");
	}

}
