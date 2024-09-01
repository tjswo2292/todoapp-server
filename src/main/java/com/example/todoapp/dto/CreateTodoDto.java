package com.example.todoapp.dto;

import lombok.Data;

// 엔드포인트에 파라미터, 바디 등 정보를 전달하는데 그 것들의 정보
@Data
public class CreateTodoDto {
    private String newTodoTitle;
}
