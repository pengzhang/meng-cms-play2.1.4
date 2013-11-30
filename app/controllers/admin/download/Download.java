package controllers.admin.download;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.download.*;

public class Download extends Controller {
	
	public static Result add(){
		return ok(download.render());
	}
	
	public static Result edit(String code){
		return ok(download.render());
	}
	
	public static Result create(){
		return redirect("/admin/download");
	}
	
	public static Result update(){
		return redirect("/admin/download");
	}
	
	public static Result delete(String code){
		return redirect("/admin/download");
	}

}
