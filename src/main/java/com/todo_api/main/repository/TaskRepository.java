package com.todo_api.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo_api.main.model.TaskModel;



public interface TaskRepository extends JpaRepository<TaskModel, Long>{

}
