package com.codingdojo.projectmanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.codingdojo.projectmanager.models.LoginUser;
import com.codingdojo.projectmanager.models.Project;
import com.codingdojo.projectmanager.models.Task;
import com.codingdojo.projectmanager.models.User;
import com.codingdojo.projectmanager.services.ProjectService;
import com.codingdojo.projectmanager.services.TaskService;
import com.codingdojo.projectmanager.services.UserService;

@Controller
public class ViewsController {
	
	@Autowired
	UserService userService;
	@Autowired
	ProjectService projectService;
	@Autowired
	TaskService taskService;
	
//	LOGIN AND REGISTER PAGE
	@GetMapping("/")
	public String loginregister(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "LoginRegister.jsp";
	}
	
	
	
	
//	DASHBOARD PAGE
	@GetMapping("/dashboard")
	public String dashboard(
			HttpSession session,
			Model model,
			@ModelAttribute("project") Project project
			) {
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}
		
		List<Project> availableProjects=projectService.findAllJoinableProjects((Long) session.getAttribute("currentID"));
		List<Project> myProjects= projectService.findMyProjects((Long) session.getAttribute("currentID"));
		
		model.addAttribute("availableProjects",availableProjects);
		model.addAttribute("myProjects",myProjects);
		
		return "Dashboard.jsp";
		
	}
	
	
//	ADD NEW PROJECT PAGE
	@GetMapping("/newProject")
	public String newProject(HttpSession session,
			Model model,
			@ModelAttribute("project") Project project,@ModelAttribute("user") User user) {
		System.out.println(project.getId());
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}
		else return "NewProject.jsp";
	}
	
	
//	READ ONE PROJECT PAGE
	@GetMapping("/project/{id}")
	public String project(HttpSession session,
			Model model,@PathVariable ("id") Long id
			) {
		
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}
		for( User teamMember:projectService.findOneById(id).getUsers() ) {
			if( teamMember.getId() == session.getAttribute("currentID")){
				model.addAttribute("teamMember","true");
			}
		}
		model.addAttribute("thisProject",projectService.findOneById(id));
		return "ProjectDetails.jsp";
	}
	
	
//	EDIT PROJECT PAGE
	@GetMapping("/editProject/{id}")
	public String editProject(
			Model model, HttpSession session,
			@PathVariable("id") Long id
			) {
		Project check=projectService.findOneById(id);
		if (check!=null) {
			if (check.getLead().getId() != session.getAttribute("currentID")) {
				return "redirect:/dashboard";
			}
				model.addAttribute("project",check);
				model.addAttribute("temp",projectService.findOneById(id).getTitle());
				return "EditProject.jsp";
			}
		else return "redirect:/dashboard";
		
		
	}
	
//	TASK PAGE
	@GetMapping("/tasks/project/{id}")
	public String projectTask(
			@ModelAttribute("task") Task task,
			@PathVariable("id") Long id,
			Model model,
			HttpSession session) {
		
		System.out.println(task.getId());
		Project check=projectService.findOneById(id);
		if (check!=null) {
			for (User teamMember:check.getUsers() ) {
				if( teamMember.getId() == session.getAttribute("currentID")){
					model.addAttribute("thisProject",check);
					model.addAttribute("allTasks",taskService.getThisProjectsTask(id));
					System.out.println("accessing tasks");
					System.out.println(task.getId());
					return "Task.jsp";
				}
			}
			return "redirect:/dashboard";
		}
		else return "redirect:/dashboard";
		
	}
	
	
	
	
	

}
