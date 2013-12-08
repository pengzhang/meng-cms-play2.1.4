package controllers.admin.news;

import java.util.List;
import java.util.Map;

import models.news.NewsCategory;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.news.news_category;

public class AdminNewsCategory extends Controller {
	
	public static Result add(){
		List<NewsCategory> ncs = NewsCategory.getNewsCategoryAll();
		return ok(news_category.render(ncs,null,true));
	}
	
	public static Result edit(String code){
		List<NewsCategory> ncs = NewsCategory.getNewsCategoryAll();
		NewsCategory nc = NewsCategory.getNewsCategoryByCode(code);
		return ok(news_category.render(ncs,nc,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		NewsCategory ac = new NewsCategory();
		ac.category_title = map.get("category_title");
		String is_channel = map.get("is_channel");
		ac.parent_category_code = map.get("parent_category_code");
		System.out.println(ac.parent_category_code);
		if(is_channel!=null && is_channel.equals("on")){
			ac.is_channel = true;
		}
		NewsCategory.createNewsCategory(ac);
		
		return redirect("/admin/news/category");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		NewsCategory nc = new NewsCategory();
		nc.category_code = map.get("category_code");
		nc.category_title = map.get("category_title");
		String is_channel = map.get("is_channel");
		nc.parent_category_code = map.get("parent_category_code");
		if(is_channel!=null && is_channel.equals("on")){
			nc.is_channel = true;
		}
		NewsCategory.modifyNewsCategory(nc);
		return redirect("/admin/news/category");
	}
	
	public static Result delete(String code){
		NewsCategory.deleteNewsCategoryByCode(code);
		return redirect("/admin/news/category");
	}

}
