package controllers.admin.download;

import java.util.List;
import java.util.Map;

import models.download.Download;
import models.download.DownloadCategory;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import utils.StringUtils;
import views.html.admin.download.*;

public class AdminDownload extends Controller {
	
	public static Result add(){
		List<DownloadCategory> ldc = DownloadCategory.getDownCategoryAll();
		return ok(download.render(ldc, null, true));
	}
	
	public static Result edit(String code){
		List<DownloadCategory> ldc = DownloadCategory.getDownCategoryAll();
		Download dl = Download.getDownload(code);
		return ok(download.render(ldc, dl, false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Download dl = new Download();
		dl.soft_desc = map.get("soft_desc");
		dl.soft_name = map.get("soft_name");
		dl.soft_author = map.get("soft_author");
		dl.soft_lang = map.get("soft_lang");
		dl.soft_size = map.get("soft_size");
		dl.soft_sys = map.get("soft_sys");
		dl.soft_type = map.get("soft_type");
		dl.soft_url = map.get("soft_url");
		dl.soft_ver = map.get("soft_ver");
		dl.down_category_code = map.get("down_category_code");
		if(map.get("update_date") == null || map.get("update_date").equals("")){
			dl.update_date = StringUtils.convertUtiltoSqlDate(StringUtils.getStanderDate(), "yyyy-MM-dd");
		}else{
			dl.update_date = StringUtils.convertUtiltoSqlDate(map.get("update_date"), "yyyy-MM-dd");
		}
		
		Download.createDownload(dl);
		return redirect("/admin/download/view/"+dl.down_category_code);
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Download dl = new Download();
		dl.soft_desc = map.get("soft_desc");
		dl.soft_name = map.get("soft_name");
		dl.soft_author = map.get("soft_author");
		dl.soft_lang = map.get("soft_lang");
		dl.soft_size = map.get("soft_size");
		dl.soft_sys = map.get("soft_sys");
		dl.soft_type = map.get("soft_type");
		dl.soft_url = map.get("soft_url");
		dl.soft_ver = map.get("soft_ver");
		dl.down_category_code = map.get("down_category_code");
		if(map.get("update_date") == null || map.get("update_date").equals("")){
			dl.update_date = StringUtils.convertUtiltoSqlDate(StringUtils.getStanderDate(), "yyyy-MM-dd");
		}else{
			dl.update_date = StringUtils.convertUtiltoSqlDate(map.get("update_date"), "yyyy-MM-dd");
		}
		dl.soft_code = map.get("soft_code");
		Download.modifyDownload(dl);
		return redirect("/admin/download/view/"+dl.down_category_code);
	}
	
	public static Result delete(String code){
		Download.deleteDownload(code);
		String dcc = Download.getDownload(code).down_category_code;
		return redirect("/admin/download/view/"+dcc);
	}
	
	public static Result audit(String code){
		Download.auditDownload(code);
		String dcc = Download.getDownload(code).down_category_code;
		return redirect("/admin/download/view/"+dcc);
	}

}
