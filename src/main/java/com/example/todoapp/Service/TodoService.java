package com.example.todoapp.Service;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// controller에서 사용할 서비스 로직들
@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo (String newTodoTitle) {
        Todo todo = new Todo();
        todo.setDescription(newTodoTitle);
        return todoRepository.save(todo);
    }

    public void deleteTodo(String todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("실패"));
        todoRepository.delete(todo);
    }
}
