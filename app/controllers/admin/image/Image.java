package controllers.admin.image;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.image.*;

public class Image extends Controller {
	
	public static Result add(){
		return ok(image.render());
	}
	
	public static Result edit(String code){
		return ok(image.render());
	}
	
	public static Result create(){
		return redirect("/admin/image");
	}
	
	public static Result update(){
		return redirect("/admin/image");
	}
	
	public static Result delete(String code){
		return redirect("/admin/image");
	}

}
