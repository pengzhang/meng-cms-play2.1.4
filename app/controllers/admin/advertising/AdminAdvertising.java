package controllers.admin.advertising;

import java.text.ParseException;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import models.adverties.Advertising;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import utils.StringUtils;

public class AdminAdvertising extends Controller {
	
	public static Result add(){
		return ok(advertising.render(null,true));
	}
	
	public static Result edit(String code){
		Advertising ad = Advertising.get_ADByCode(code);
		return ok(advertising.render(ad,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Advertising ad = new Advertising();
		ad.ad_position = map.get("ad_position");
		ad.ad_url = map.get("ad_url");
		ad.ad_script = map.get("ad_script");
		ad.ad_type = map.get("ad_type");
		ad.ad_image = map.get("ad_image");
		ad.ad_text = map.get("ad_text");
		ad.ad_url = map.get("ad_url");
		ad.endate = StringUtils.convertUtiltoSqlDate(map.get("endate"), "yyyy-MM-dd");
		ad.web_site_code = map.get("web_site_code");
		Advertising.create_AD(ad);
		return redirect("/admin/advertising");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Advertising ad = new Advertising();
		ad.ad_code = map.get("ad_code");
		ad.ad_position = map.get("ad_position");
		ad.ad_url = map.get("ad_url");
		ad.ad_script = map.get("ad_script");
		ad.ad_type = map.get("ad_type");
		ad.ad_image = map.get("ad_image");
		ad.ad_text = map.get("ad_text");
		ad.ad_url = map.get("ad_url");
		ad.endate = StringUtils.convertUtiltoSqlDate(map.get("endate"), "yyyy-MM-dd");
		ad.web_site_code = map.get("web_site_code");
		Advertising.modify_AD(ad);
		return redirect("/admin/advertising");
	}
	
	public static Result delete(String code){
		Advertising.delete_AD(code);
		return redirect("/admin/advertising");
	}
	
	public static Result online(String code){
		Advertising.online_AD(code);
		return redirect("/admin/advertising");
	}
	
	public static Result offline(String code){
		Advertising.offline_AD(code);
		return redirect("/admin/advertising");
	}

}
