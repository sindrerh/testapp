package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import views.html.*;
import models.*;

public class Application extends Controller {

	public static Result index() {
		//return ok(index.render("pedroApp er klar!."));
		return redirect(routes.Application.tasks());
	}
	
	public static Result tasks() {
		  return ok(
		    views.html.index.render(Task.all(), taskForm)
		  );
		}
	
		  
		  public static Result newTask() {
			  Form<Task> filledForm = taskForm.bindFromRequest();
			  if(filledForm.hasErrors()) {
			    return badRequest(
			      views.html.index.render(Task.all(), filledForm)
			    );
			  } else {
			    Task.create(filledForm.get());
			    return redirect(routes.Application.tasks());  
			  }
		  }
		  
		  public static Result deleteTask(Long id) {
		    Task.delete(id);
		    return redirect(routes.Application.tasks());  
		  }	

	
	static Form<Task> taskForm = form(Task.class);	

}