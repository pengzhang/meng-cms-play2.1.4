package controllers.admin.news;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.news.*;

public class NewsCategory extends Controller {
	
	public static Result add(){
		return ok(news_category.render());
	}
	
	public static Result edit(String code){
		return ok(news_category.render());
	}
	
	public static Result create(){
		return redirect("/admin/news/category");
	}
	
	public static Result update(){
		return redirect("/admin/news/category");
	}
	
	public static Result delete(String code){
		return redirect("/admin/news/category");
	}

}
