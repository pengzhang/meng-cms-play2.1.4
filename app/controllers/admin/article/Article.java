package controllers.admin.article;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.article.*;

public class Article extends Controller {
	
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
