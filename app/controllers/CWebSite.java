package controllers;

import java.util.Map;

import models.WebSite;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

public class CWebSite extends Controller{
	
	public static Result modify(){
		Map<String, String> map = DynamicForm.form().bindFromRequest().data();
		String website = map.get("website");
		String domain = map.get("domain");
		String webcode = map.get("webcode");
		WebSite.modifyWebSite(new WebSite(website,domain,webcode));
		return ok();
	}

}
