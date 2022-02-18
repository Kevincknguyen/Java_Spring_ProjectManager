package com.codingdojo.projectmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingdojo.projectmanager.models.Task;
import com.codingdojo.projectmanager.repositories.TaskRepository;

@Service
public class TaskService {

	
	private final TaskRepository taskRepo;
	public TaskService(TaskRepository taskRepo) {
		this.taskRepo=taskRepo;
	}
	
	
	public Task createTask(Task task) {
		return taskRepo.save(task);
		
	}
	
public List<Task> getThisProjectsTask(Long id){
	return taskRepo.findAllByProject_IdOrderByCreatedAtDesc(id);
}
	
	
	
	
	
	
}
