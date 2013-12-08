package controllers.admin.image;

import java.io.File;
import java.util.List;
import java.util.Map;

import models.image.Image;
import models.image.ImageCategory;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.admin.image.*;

public class AdminImage extends Controller {
	
	public static Result add(){
		List<ImageCategory> lic = ImageCategory.getImageCategoryAll();
		return ok(image.render(lic,null,true));
	}
	
	public static Result edit(String code){
		List<ImageCategory> lic = ImageCategory.getImageCategoryAll();
		Image img = Image.getImage(code);
		return ok(image.render(lic,img,false));
	}
	
	public static Result create(){
		
		MultipartFormData body = request().body().asMultipartFormData();
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Image img = new Image();
		img.category_code = map.get("category_code");
		img.image_desc = map.get("image_desc");
		img.image_name = map.get("image_name");
		img.image_path = map.get("image_path");
		img.image_type = map.get("image_type");
		if(img.image_type.equalsIgnoreCase("upload")){
			FilePart picture = body.getFile("picture");
			  if (picture != null) {
//			    String fileName = picture.getFilename();
//			    String contentType = picture.getContentType(); 
			    img.image_file = picture.getFile();
			  } 
		}
		img.img_code = map.get("img_code");
		img.image_url = map.get("image_url");
		Image.createImage(img);
		return redirect("/admin/image");
	}
	
	public static Result update(){
		MultipartFormData body = request().body().asMultipartFormData();
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Image img = new Image();
		img.article_code = map.get("article_code");
		img.category_code = map.get("category_code");
		img.image_desc = map.get("image_desc");
		img.image_name = map.get("image_name");
		img.image_path = map.get("image_path");
		img.image_type = map.get("image_type");
		if(img.image_type.equalsIgnoreCase("upload")){
			FilePart picture = body.getFile("picture");
			  if (picture != null) {
//			    String fileName = picture.getFilename();
//			    String contentType = picture.getContentType(); 
			    img.image_file = picture.getFile();
			  } 
		}
		img.img_code = map.get("img_code");
		img.image_url = map.get("image_url");
		Image.modifyImage(img);
		return redirect("/admin/image");
	}
	
	public static Result delete(String code){
		Image.deleteImage(code);
		return redirect("/admin/image");
	}
	
	public static Result audit(String code){
		Image.auditImage(code);
		return redirect("/admin/image");
	}

}
