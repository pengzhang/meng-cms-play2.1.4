package controllers.admin.download;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.download.*;

public class DownloadCategory extends Controller {
	
	public static Result add(){
		return ok(download_category.render());
	}
	
	public static Result edit(String code){
		return ok(download_category.render());
	}
	
	public static Result create(){
		return redirect("/admin/download/category");
	}
	
	public static Result update(){
		return redirect("/admin/download/category");
	}
	
	public static Result delete(String code){
		return redirect("/admin/download/category");
	}

}
