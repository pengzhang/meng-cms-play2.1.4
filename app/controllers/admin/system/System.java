package controllers.admin.system;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.system.*;

public class System extends Controller {
	
	public static Result add(){
		return ok(system.render());
	}
	
	public static Result edit(String code){
		return ok(system.render());
	}
	
	public static Result create(){
		return redirect("/admin/system");
	}
	
	public static Result update(){
		return redirect("/admin/system");
	}
	
	public static Result delete(String code){
		return redirect("/admin/system");
	}

}
