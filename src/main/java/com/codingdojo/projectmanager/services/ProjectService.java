package com.codingdojo.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.projectmanager.models.Project;
import com.codingdojo.projectmanager.models.User;
import com.codingdojo.projectmanager.repositories.ProjectRepository;
import com.codingdojo.projectmanager.repositories.UserRepository;

@Service
public class ProjectService {
	
	private final ProjectRepository projectRepo;
	private final UserRepository userRepo;
	public ProjectService(ProjectRepository projectRepo,UserRepository userRepo) {
		this.projectRepo=projectRepo;
		this.userRepo=userRepo;
	}

	
	
//	READ ONE
	public Project findOneById(Long id) {
		Optional<Project> thisProject=projectRepo.findById(id);
		if (thisProject.isPresent()) {
			return thisProject.get();
		}
		System.out.println("no project exits");
		return null;
	}
	
//	READ ALL PROJECTS THAT DO NOT HAVE THIS USER ID INSIDE THE USERS LIST
	public List<Project> findAllJoinableProjects(Long id){
	Optional<User> thisUser=userRepo.findById(id);
	if (thisUser!=null) {
		return projectRepo.findByUsersNotContains(thisUser.get());
		}
		else return null;
			
		}
			
//	READ ALL PROJECTS THAT DO HAVE THIS USER ID IN ITS TEAM
	public List<Project> findMyProjects(Long id){
		Optional<User> thisUser=userRepo.findById(id);
		if (thisUser.isPresent()) {
			return projectRepo.findAllByUsersContaining(thisUser.get());
			}
			else return null;
				
			}
	
//	CREATE PROJECT
	public Project createProject(Project project) {
		return projectRepo.save(project);
	}
	
	
//	UPDATE PROJECT DETAILS
	public Project updateProject(Project project) {
		Optional<Project> isProject=projectRepo.findById(project.getId());
		if (isProject.isPresent()) {
			Project edit=isProject.get();
			edit.setTitle(project.getTitle());
			edit.setDescription(project.getDescription());
			edit.setDueDate(project.getDueDate());
			projectRepo.save(edit);
			return edit;
		}
		else return null;
//		return projectRepo.save(project);
	}
//	UPDATE BY ADDING SPECIFIC USER TO PROJECTS USERS
	public Project addUser(Project project, Long id) {
		
		User newUser=project.getUsers().get(0);
		Project thisProject= this.findOneById(id);
		if (thisProject!=null) {
			System.out.println(thisProject.getUsers());
			thisProject.getUsers().add(newUser);
			System.out.println(thisProject.getUsers());
			projectRepo.save(thisProject);
			
			return thisProject;	
		}
		else return null;
	
	}
	

//	UPDATE BY REMOVING SPECIFIC USER FORM PROJECT
	
	public Project removeUser(Project project,Long id) {
		User removeUser=project.getUsers().get(0);
		Project thisProject= this.findOneById(id);
		if (thisProject!=null) {
			System.out.println(thisProject.getUsers());
			thisProject.getUsers().remove(removeUser);
			System.out.println(thisProject.getUsers());
			projectRepo.save(thisProject);
			
			return thisProject;	
		}
		else return null;
	}
	
	
	
	
//	DELETE Project BY ITS ID
	public String deleteProject(Long id) {
	     Optional<Project> optional = projectRepo.findById(id);
	     if(optional.isPresent()) {
	    	 projectRepo.deleteById(id);
	         return "Completed delete";
	         
	     } 
	     else {
	    	 return "No expense with ID found";
	     }
	 	}
	
	
	
	
	
	
	
	
	
	
	
	
}
