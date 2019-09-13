package com.jcombat.services;

import com.jcombat.entity.TodoModel;
import com.jcombat.repositry.TodoRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl {
@Autowired
private TodoRepositry todoRepositry;

public TodoModel save(TodoModel todoModel){
    return  todoRepositry.save(todoModel);
}

    public TodoModel getTodoById(String tid){
        return  todoRepositry.findOne(tid);

    }
public  void  deleteTodoById(String tid){
      todoRepositry.delete(tid);

}


    public void saveAllTodo(List<TodoModel> todoList) {
        for (TodoModel todoModel : todoList){
              todoRepositry.save(todoModel);
        }
    }
}
