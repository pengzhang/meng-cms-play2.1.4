package controllers.admin.download;

import java.util.List;
import java.util.Map;

import models.download.DownloadCategory;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

public class AdminDownloadCategory extends Controller {
	
	public static Result add(){
		//TODO web_site_code
		return ok(download_category.render(null, true));
	}
	
	public static Result edit(String code){
		//TODO web_site_code
		List<DownloadCategory> ldc = DownloadCategory.getDownCategoryAll();
		return ok(download_category.render(ldc,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		DownloadCategory dc = new DownloadCategory();
		dc.dc_name = map.get("dc_name");
		dc.dc_desc = map.get("dc_desc");
		dc.parent_dc_code = map.get("parent_dc_code");
		dc.web_site_code = map.get("web_site_code");
		DownloadCategory.createDownCategory(dc);
		return redirect("/admin/download/category");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		DownloadCategory dc = new DownloadCategory();
		dc.dc_name = map.get("dc_name");
		dc.dc_desc = map.get("dc_desc");
		dc.parent_dc_code = map.get("parent_dc_code");
		dc.web_site_code = map.get("web_site_code");
		dc.dc_code = map.get("dc_code");
		DownloadCategory.modifyDownCategory(dc);
		return redirect("/admin/download/category");
	}
	
	public static Result delete(String code){
		DownloadCategory.deleteDownCategory(code);
		return redirect("/admin/download/category");
	}

}
