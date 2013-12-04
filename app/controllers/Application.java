package controllers;

import models.article.ArticleCategory;
import play.mvc.Controller;
import play.mvc.Result;
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
//    	WebSite.saveWebSite(new WebSite(StringUtils.getMengCode(),AppConfig.WebSiteName,request().remoteAddress()));
    	
    	//文章分类初始化
    	ArticleCategory ac = new ArticleCategory();
    	ac.category_code = "default";
    	ac.category_title = "默认分类";
    	ArticleCategory.createArticleCategory(ac);
    	
    	return ok("{\"init_status\":\"success\"}");
    }
  
}
