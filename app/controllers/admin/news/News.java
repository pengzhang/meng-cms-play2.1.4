package controllers.admin.news;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.news.*;

public class News extends Controller {
	
	public static Result add(){
		return ok(news.render());
	}
	
	public static Result edit(String code){
		return ok(news.render());
	}
	
	public static Result create(){
		return redirect("/admin/news");
	}
	
	public static Result update(){
		return redirect("/admin/news");
	}
	
	public static Result delete(String code){
		return redirect("/admin/news");
	}

}
