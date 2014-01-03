import models.article.ArticleCategory;
import models.news.NewsCategory;
import models.users.Administrator;


public class MengCMS {
	
	public static void init(){
		//文章分类初始化
		if(ArticleCategory.getArticleCategoryByCode("default") == null){
	    	ArticleCategory ac = new ArticleCategory();
	    	ac.category_code = "default";
	    	ac.category_title = "默认分类";
	    	ArticleCategory.createArticleCategory(ac);
		}
		
		if(NewsCategory.getNewsCategoryByCode("default") == null){
			NewsCategory nc = new NewsCategory();
			nc.category_code = "default";
			nc.category_title = "默认分类";
			NewsCategory.createNewsCategory(nc);
		}
    	
    	//默认管理员
    	if(Administrator.getAdminUserByUsername("admin") == null){
	    	Administrator admin = new Administrator();
	    	admin.username = "admin";
	    	admin.password = "admin";
	    	admin.email = "admin@admin.com";
	    	admin.real_name = "Administrator";
	    	Administrator.createUser(admin);
    	}
	}

}
