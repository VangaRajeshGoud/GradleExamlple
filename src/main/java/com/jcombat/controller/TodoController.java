package com.jcombat.controller;

import com.jcombat.entity.TodoModel;
import com.jcombat.services.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    TodoServiceImpl todoService;
    @GetMapping("/getTodos")
    public List<TodoModel> getTodos() {
        RestTemplate restTemplate=new RestTemplate();
        String theUrl = "https://jsonplaceholder.typicode.com/todos";
        ResponseEntity<List<TodoModel>> response = restTemplate.exchange(theUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<TodoModel>>() {
        });
        List<TodoModel> todoList = response.getBody();
        todoService.saveAllTodo(todoList);
        return todoList;
    }

    @GetMapping(value = "/getTodoById/{tid}")
    public TodoModel getTodoById(@PathVariable("tid") Integer tid){
System.out.println("tid "+tid);
        return todoService.getTodoById(String.valueOf(tid));

    }
    @DeleteMapping(value = "/deletetodoByid/{tid}")
    public String deletetodoByid(@PathVariable("tid") String tid){
        todoService.deleteTodoById(tid);
        return  "Successfully deleted ID : "+tid;
    }
}
