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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="users")
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		@NotEmpty(message="name is required!")
		@Size(min = 3, max = 200, message="Please provide valid name between 3-200 characters")
		private String firstName;
		
		@NotEmpty(message="name is required!")
		@Size(min = 3, max = 200, message="Please provide valid name between 3-200 characters")
		private String lastName;
		
		@NotEmpty(message="Email is required")
		@Email(message="Please provide a valid email")
		private String email;
		
		
		@NotEmpty
		@Size(min = 8, max = 200, message="Please provide valid password between 8-200 characters")
		private String password;
		
		@Transient
		@NotEmpty(message="Confirm password is required")
		@Size(min = 8, max = 200, message="Please provide valid password between 8-200 characters")
		private String confirm;
		
		
		@Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
		
	    
	    @OneToMany(mappedBy="lead",fetch=FetchType.LAZY,cascade=CascadeType.ALL) 
	    private List<Project> leads;
		
	    @OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL) 
		 private List<Task> tasks;
	    
	    @ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
		     name = "projects_users", 
		     joinColumns = @JoinColumn(name = "user_id"), 
		     inverseJoinColumns = @JoinColumn(name = "project_id"))
			private List<Project> projects;
		
    public User() {
    	
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



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getConfirm() {
		return confirm;
	}



	public void setConfirm(String confirm) {
		this.confirm = confirm;
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



	public List<Task> getTasks() {
		return tasks;
	}



	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}



	public List<Project> getProjects() {
		return projects;
	}



	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}



	public List<Project> getLeadProjects() {
		return leads;
	}



	public void setLeadProjects(List<Project> leadProjects) {
		this.leads = leadProjects;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
