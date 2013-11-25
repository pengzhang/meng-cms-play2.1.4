package controllers;

import models.WebSite;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AppConfig;
import utils.StringUtils;
import views.html.index;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
   
    /**
     * CMS初始化
     * @return
     */
    public static Result cms_init(){
    	//初始化website
    	WebSite.saveWebSite(new WebSite(StringUtils.getMengCode(),AppConfig.WebSiteName,request().remoteAddress()));
    	return ok("{\"init_status\":\"success\"}");
    }
  
}
