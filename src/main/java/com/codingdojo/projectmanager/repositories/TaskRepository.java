package com.codingdojo.projectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.projectmanager.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
	List<Task> findAllByProject_IdOrderByCreatedAtDesc(Long id);

}
