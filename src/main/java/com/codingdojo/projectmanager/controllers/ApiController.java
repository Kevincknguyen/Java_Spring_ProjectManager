package com.codingdojo.projectmanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.projectmanager.models.LoginUser;
import com.codingdojo.projectmanager.models.Project;
import com.codingdojo.projectmanager.models.Task;
import com.codingdojo.projectmanager.models.User;
import com.codingdojo.projectmanager.services.ProjectService;
import com.codingdojo.projectmanager.services.TaskService;
import com.codingdojo.projectmanager.services.UserService;

@Controller
public class ApiController {
	
	
	@Autowired
	UserService userService;
	@Autowired
	ProjectService projectService;
	@Autowired
	TaskService taskService;

	
//	REGISTER
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser")User newUser,
			BindingResult result, 
			Model model,
			HttpSession session) {
		
		
		if (result.hasErrors()) {
			model.addAttribute("newLogin",new LoginUser());
			return "LoginRegister.jsp";
		}
		
		User verifiedUser= userService.register(newUser,result);
		if (verifiedUser==null) {
			model.addAttribute("newLogin",new LoginUser());
			return "LoginRegister.jsp";
		}
		session.setAttribute("currentUser", verifiedUser);
		session.setAttribute("currentID", verifiedUser.getId());
		System.out.println("Registering account");
		return "redirect:/dashboard";
	}
	
//	LOGIN
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin")LoginUser newLogin,
			BindingResult result, 
			Model model,
			HttpSession session) {
		
			
		User verifiedUser=userService.login(newLogin,result);
		
		if (verifiedUser==null || result.hasErrors()) {
			model.addAttribute("newUser",new User());
			return "LoginRegister.jsp";
		
		}
		session.setAttribute("currentUser", verifiedUser);
		session.setAttribute("currentID", verifiedUser.getId());
		System.out.println("Logging in");
		return "redirect:/dashboard";
	}
	
//	LOGOUT
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}
	
//	UPDATE PROJECT FOR NEWUSER TO JOIN THE USERS
	@PutMapping("/addUserToProject/{id}")
	public String addUserToProject(
			@PathVariable ("id") Long id,
			@ModelAttribute ("project") Project project
			) {
		
		projectService.addUser(project,id);
		return "redirect:/dashboard";	
	}
	
	
//	UPDATE PROJECT BY REMOVING A SPECIFIC USER
	@PutMapping("/removeUserToProject/{id}")
	public String removeUserfromProject(
			@PathVariable ("id") Long id,
			@ModelAttribute ("project") Project project
			) {
		
		projectService.removeUser(project,id);
		return "redirect:/dashboard";	
	}
	
	
//	UPDATE PROJECT DETAILS
	@PutMapping("/api/edit/{id}")
	public String editProejct(Model model,
			@PathVariable ("id") Long id,
			@Valid @ModelAttribute("project")Project project,
			BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAttribute("temp",projectService.findOneById(id).getTitle());
			return "EditProject.jsp";
		} else {
			projectService.updateProject(project);
			return "redirect:/dashboard";
		}
		
		
	}
	

//	CREATE PROJECT
	@PostMapping("/api/newProject")
	public String newProject(
			@Valid @ModelAttribute("project") Project project,
			BindingResult result) {

		if(result.hasErrors()) {
			return "NewProject.jsp";
		}
		projectService.createProject(project);
		return"redirect:/dashboard";
	}

//	CREATE TASK
	@PostMapping("/api/newTask/{aid}")
	public String newTask(
			@PathVariable ("aid") Long id,
			@Valid @ModelAttribute("task") Task task,
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			Project check=projectService.findOneById(id);
			model.addAttribute("allTasks",taskService.getThisProjectsTask(id));
			model.addAttribute("thisProject",check);
			return "Task.jsp";
		}
		else {
		taskService.createTask(task);
		System.out.println("This is creating a task");
		
		
		return "redirect:/tasks/project/{aid}";
		}
	}
	
	
	
	
//	DELETE Project
	@DeleteMapping("/deleteproject/{id}")
	public String deleteProject(@PathVariable("id")Long id) {
		projectService.deleteProject(id);
		return "redirect:/dashboard";
	}
	
			
			
}
	
	

