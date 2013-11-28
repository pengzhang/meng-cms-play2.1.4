package controllers.admin.advertising;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.advertising.*;

public class Advertising extends Controller {
	
	public static Result add(){
		return ok(advertising.render());
	}
	
	public static Result edit(String code){
		return ok(advertising.render());
	}
	
	public static Result create(){
		return redirect("/admin/advertising");
	}
	
	public static Result update(){
		return redirect("/admin/advertising");
	}
	
	public static Result delete(String code){
		return redirect("/admin/advertising");
	}

}
