package com.project.newspaper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel<T>{
    private String status;
    private String message;
    private T data;
}
