package com.example.todoapp.Controller;

import com.example.todoapp.Service.TodoService;
import com.example.todoapp.domain.Todo;
import com.example.todoapp.dto.CreateTodoDto;
import com.example.todoapp.dto.TodoDto;
//import com.example.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 엔드포인트들 정의하는 곳
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/")
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @Transactional // for dirty checking
    @PatchMapping("/patch-todo")
    public void patchTodo(@RequestBody TodoDto todoDto) {
        todoService.patchTodo(todoDto);
    }

    @PostMapping("/create-todo")
    public String createTodo(@RequestBody CreateTodoDto newTodoTitle) {
        todoService.createTodo(newTodoTitle);
        return "OK";
    }

    @DeleteMapping("/delete-todo/{todoId}")
    public void deleteTodo(@PathVariable String todoId) {
        todoService.deleteTodo(todoId);
    }
}
