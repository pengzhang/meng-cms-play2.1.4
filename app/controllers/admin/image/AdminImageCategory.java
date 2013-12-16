package controllers.admin.image;

import java.util.List;
import java.util.Map;

import models.image.ImageCategory;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.image.*;

public class AdminImageCategory extends Controller {
	
	public static Result add(){
		List<ImageCategory> lic = ImageCategory.getImageCategoryAll();
		return ok(image_category.render(lic,null,true));
	}
	
	public static Result edit(String code){
		List<ImageCategory> lic = ImageCategory.getImageCategoryAll();
		ImageCategory ic = ImageCategory.getImageCategory(code);
		return ok(image_category.render(lic,ic,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		ImageCategory ic = new ImageCategory();
		ic.category_name = map.get("category_name");
		ic.category_desc = map.get("category_desc");
		ic.parent_category_code = map.get("parent_category_code");
		if(map.get("is_channel") == null) {
			ic.is_channel = false;
		}else if(map.get("is_channel").equals("on")){
			ic.is_channel = true;
		}
		ic.web_site_code = map.get("web_site_code");
		ImageCategory.createImageCategory(ic);
		return redirect("/admin/image/category");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		ImageCategory ic = new ImageCategory();
		ic.ic_code = map.get("ic_code");
		ic.category_name = map.get("category_name");
		ic.category_desc = map.get("category_desc");
		ic.parent_category_code = map.get("parent_category_code");
		if(map.get("is_channel") == null) {
			ic.is_channel = false;
		}else if(map.get("is_channel").equals("on")){
			ic.is_channel = true;
		}		ic.web_site_code = map.get("web_site_code");
		ImageCategory.modifyImageCategory(ic);
		return redirect("/admin/image/category");
	}
	
	public static Result delete(String code){
		ImageCategory.deleteImageCategory(code);
		return redirect("/admin/image/category");
	}

}
