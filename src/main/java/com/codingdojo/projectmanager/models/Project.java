package com.codingdojo.projectmanager.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="projects")
public class Project {

	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		@NotEmpty(message="name is required!")
		@Size(min = 3, max = 200, message="Please provide valid name between 3-200 characters")
		private String title;
		
		
		
		@NotEmpty(message="Description is required!")
		@Size(min = 3, max = 200, message="Please provide valid description between 3-200 characters")
		private String description;
		
		
		@NotNull
		@Future
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date dueDate;
		
		
		
		@Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
	
	    
	    @OneToMany(mappedBy="project",fetch=FetchType.LAZY,cascade=CascadeType.ALL) 
		 private List<Task> tasks;
	    
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="lead_id")
	    private User lead;
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
		     name = "projects_users", 
		     joinColumns = @JoinColumn(name = "project_id"), 
		     inverseJoinColumns = @JoinColumn(name = "user_id"))
			private List<User> users;
		
		
	
	
	public Project() {
		
	}
	
	
	public User getLead() {
		return lead;
	}


	public void setLead(User user) {
		this.lead = user;
	}


	@PrePersist
    protected void onCreate() {
    	this.createdAt=new Date();
    }
    
	@PreUpdate
	protected void onUpdate() {
	this.updatedAt=new Date();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public List<Task> getTasks() {
		return tasks;
	}


	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
