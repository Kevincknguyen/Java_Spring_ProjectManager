package com.codingdojo.projectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.projectmanager.models.Project;
import com.codingdojo.projectmanager.models.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
	List<Project> findAll();
//	List<Project> findByUser_id(Long id);
//	List<Project> findAllByUser_idContaining(Long id);
	List<Project> findAllByUsersContaining(User user);
	List<Project> findByUsersNotContains(User user);


}
