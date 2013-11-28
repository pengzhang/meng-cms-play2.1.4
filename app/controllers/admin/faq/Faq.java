package controllers.admin.faq;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.faq.*;

public class Faq extends Controller {
	
	public static Result add(){
		return ok(faq.render());
	}
	
	public static Result edit(String code){
		return ok(faq.render());
	}
	
	public static Result create(){
		return redirect("/admin/faq");
	}
	
	public static Result update(){
		return redirect("/admin/faq");
	}
	
	public static Result delete(String code){
		return redirect("/admin/faq");
	}

}
