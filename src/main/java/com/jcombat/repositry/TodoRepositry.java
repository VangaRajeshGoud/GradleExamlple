package com.jcombat.repositry;

import com.jcombat.entity.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepositry extends JpaRepository<TodoModel,String> {
}
