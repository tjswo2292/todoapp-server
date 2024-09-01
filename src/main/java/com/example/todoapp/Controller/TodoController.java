package com.example.todoapp.Controller;

import com.example.todoapp.Service.TodoService;
import com.example.todoapp.domain.Todo;
import com.example.todoapp.dto.CreateTodoDto;
import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// 엔드포인트들 정의하는 곳
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    private final TodoService todoService;

    @GetMapping("/")
    public List<Todo> index() {
        return todoRepository.findAll();
    }

    @Transactional // for dirty checking
    @PatchMapping("/patch-todo")
    public String patchTodo(@RequestBody TodoDto todoDto) {
        Optional<Todo> todoContents = todoRepository.findById(todoDto.getTodoId());

        if (todoContents.isPresent()) {
            Todo todo = todoContents.get();
            todo.setDescription(todoDto.getTodoTitle());

            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    @PostMapping("/create-todo")
    public ResponseEntity<Todo> createTodo(@RequestBody CreateTodoDto newTodoTitle) {
        Todo createdTodo = todoService.createTodo(newTodoTitle.getNewTodoTitle());
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-todo/{todoId}")
    public String deleteTodo(@PathVariable String todoId) {
        todoService.deleteTodo(todoId);
        return "OK";
    }
}
