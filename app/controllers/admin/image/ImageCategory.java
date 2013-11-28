package controllers.admin.image;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.image.*;

public class ImageCategory extends Controller {
	
	public static Result add(){
		return ok(image_category.render());
	}
	
	public static Result edit(String code){
		return ok(image_category.render());
	}
	
	public static Result create(){
		return redirect("/admin/image/category");
	}
	
	public static Result update(){
		return redirect("/admin/image/category");
	}
	
	public static Result delete(String code){
		return redirect("/admin/image/category");
	}

}
