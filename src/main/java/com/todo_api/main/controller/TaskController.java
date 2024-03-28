package com.todo_api.main.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.todo_api.main.model.TaskModel;
import com.todo_api.main.repository.TaskRepository;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskRepository taskrepository;
	
	@GetMapping
	public List<TaskModel> listarTasks(){
		return taskrepository.findAll();
	}
	
	@PostMapping
	public TaskModel criarTask(@RequestBody TaskModel task) {
		task.setDataCriacao(new Date());
		
		return taskrepository.save(task);
	}
	
	@PatchMapping
	public TaskModel atualizarTask(@RequestBody TaskModel taskAtu, @PathVariable Long id) {
		return taskrepository.findById(id)
				.map(task ->{
					task.setMensagem(taskAtu.getMensagem());
					task.setStatus(taskAtu.GetStatus());
					task.setDataConclusao(taskAtu.getDataConclusao());
					return taskrepository.save(task);
				})
		.orElseThrow(() -> new RuntimeException("Task não encontrada com o ID: " + id));
	}
	
	@DeleteMapping("/{id}")
	public String deletarTask(@PathVariable Long id){
		TaskModel usuario1 = taskrepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
		
			taskrepository.deleteById(id);
			return "Usuario DELETADO\n" + usuario1.toString();
	}
	
}
