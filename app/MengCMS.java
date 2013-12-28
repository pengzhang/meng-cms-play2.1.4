import models.article.ArticleCategory;
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
