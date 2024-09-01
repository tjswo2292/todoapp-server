package com.example.todoapp.Service;

import com.example.todoapp.domain.Todo;
import com.example.todoapp.dto.CreateTodoDto;
import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// controller에서 사용할 서비스 로직들
@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodoList() {
        return todoRepository.findAll();
    }

    public void patchTodo(TodoDto todoDto) {
        // 여기서 어떤 값이 추가되는지
        Optional<Todo> todoContents = todoRepository.findById(todoDto.getTodoId());

        if (todoContents.isPresent()) {
            Todo todo = todoContents.get();
            todo.setDescription(todoDto.getTodoTitle());
        }
    }

    public void createTodo (CreateTodoDto newTodoTitle) {
        Todo todo = new Todo();
        todo.setDescription(newTodoTitle.getNewTodoTitle());
        todoRepository.save(todo);
    }

    public void deleteTodo(String todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("실패"));
        todoRepository.delete(todo);
    }
}
