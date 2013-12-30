import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;

import java.lang.reflect.Method;

import models.statistics.UserAgentStat;

import play.Application;
import play.GlobalSettings;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Result;
import utils.useragent.UserAgent;

public class Global extends GlobalSettings {
	
	
	public void onStart(Application app){
		MengCMS.init();
	}
	
	public void onStop(Application app){
		
	}

	public Result onError(Throwable t) {
		return internalServerError("500");
	}

	public Result onHandlerNotFound(String uri) {
		return notFound("404");
	}

	public Result onBadRequest(String uri, String error) {
		return badRequest(uri +":"+ error);
	}

	public Action<?> onRequest(Request request, Method actionMethod) {
		System.out.println("before each request..." + request.toString());
		System.out.println("before each method..." + actionMethod.getName());
		String act = actionMethod.getName();
		
		System.out.println(request.getHeader("User-Agent"));
		
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent")); 
		UserAgentStat uas = new UserAgentStat();
		uas.brower = userAgent.getBrowser().getName();
		uas.brower_version = userAgent.getBrowserVersion().getVersion();
		uas.platform = userAgent.getOperatingSystem().getName();
		UserAgentStat.createUA(uas);
		
		if (exceptAction(act)) {
			return super.onRequest(request, actionMethod);
		} else {

			return new Action.Simple() {
				public Result call(Context ctx) throws Throwable {
					System.out.println(ctx.session().get("username"));
					if (ctx.session().get("username") == null || ctx.session().get("username").equals("")) {
						return redirect("/admin");
					}
					return delegate.call(ctx);
				}
			};
		}
	}
	
	public boolean exceptAction(String act){
		String[] actions = {"index","login"};
		for(String action:actions){
			if(act.equals(action)){
				return true;
			}
		}
		return false;
	}
	
}
