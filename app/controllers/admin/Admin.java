package controllers.admin;

import java.util.List;
import java.util.Map;

import models.advertising.Advertising;
import models.article.Article;
import models.article.ArticleCategory;
import models.download.Download;
import models.download.DownloadCategory;
import models.faq.Faq;
import models.image.Image;
import models.image.ImageCategory;
import models.message.Message;
import models.news.News;
import models.news.NewsCategory;
import models.question.Exam;
import models.question.Question;
import models.statistics.MainStat;
import models.statistics.UserAgentStat;
import models.users.Administrator;
import models.users.User;
import models.users.UserProfile;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;
import views.html.admin.article.*;
import views.html.admin.question.*;
import views.html.admin.message.*;
import views.html.admin.user.*;
import views.html.admin.advertising.*;
import views.html.admin.faq.*;
import views.html.admin.image.*;
import views.html.admin.news.*;
import views.html.admin.download.*;
import views.html.admin.system.*;

public class Admin extends Controller {
	
	public static Result index(){
		return ok(index.render());
	}
	
	public static Result main(){
		MainStat ms = MainStat.getMainStat();
		List<UserAgentStat> lua = UserAgentStat.getUserAgent(0, 10);
		return ok(dashboard.render(ms,lua));
	}
	
	public static Result article(String code){
		List<Article> articles = Article.getArticlePageByCategoryCode(code, 0, 10);
		return ok(article_view.render(articles));
	}
	
	public static Result article_category(){
		List<ArticleCategory> acs = ArticleCategory.getArticleCategoryPage(0, 10);
		return ok(article_category_view.render(acs));
	}
	
	public static Result news(String code){
		List<News> news = News.getNewsPageByCategoryCode(code, 0, 10);
		return ok(news_view.render(news));
	}
	
	public static Result news_category(){
		List<NewsCategory> ncs = NewsCategory.getNewsCategoryPage(0, 10);
		return ok(news_category_view.render(ncs));
	}
	
	public static Result message(){
		List<Message> lmsg = Message.getMessagePage(0, 10);
		return ok(message_view.render(lmsg));
	}
	
	public static Result question(String code){
		List<Question> lq = Question.getExamQuestionPage(code, 0, 10);
		return ok(question_view.render(lq));
	}
	
	public static Result exam(){
		List<Exam> le = Exam.getExamPage(0, 10);
		return ok(exam_view.render(le));
	}
	
	public static Result user(){
		List<UserProfile> lup = UserProfile.getUserProfilesPage(0, 10);
		return ok(user_view.render(lup));
	}
	
	public static Result administor(){
		List<Administrator> ladmin = Administrator.getAdminUserByPage(0, 10);
		return ok(administrator_view.render(ladmin));
	}
	
	public static Result advertising(){
		List<Advertising> lad = Advertising.getAdvertisingList(0, 10);
		return ok(advertising_view.render(lad));
	}
	
	public static Result faq(){
		List<Faq> faqs = Faq.getFaqPage(0, 10);
		return ok(faq_view.render(faqs));
	}
	
	public static Result image(String code){
		List<Image> limg = Image.getImagePageByCategory(code, 0, 10);
		return ok(image_view.render(limg));
	}
	
	public static Result image_category(){
		List<ImageCategory> lic = ImageCategory.getImageCategoryPage(0, 10);
		return ok(image_category_view.render(lic));
	}
	
	public static Result download(String code){
		List<Download> ldl = Download.getDCDownloadPage(code, 0, 10);
		return ok(download_view.render(ldl));
	}
	
	public static Result download_category(){
		List<DownloadCategory> ldc = DownloadCategory.getDownCategoryPage(0, 10);
		return ok(download_category_view.render(ldc));
	}
	
	public static Result system(){
		return ok(system_view.render());
	}
	
	public static Result login(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		String username = map.get("username");
		String password	= map.get("password");
		if(Administrator.login(username, password)){
			Administrator admin = Administrator.getAdminUserByUsername(username);
			session().put("username", username);
			session().put("real_name", admin.real_name);
			return redirect("/admin/main");
		} else {
			return redirect("/admin");
		}
	}
	
	public static Result logout(){
		session().clear();
		return redirect("/admin");
	}

}
