package com.example.schoolmanagement.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {

    @NotNull(message = "id should be not empty")
    private Integer id;

    @NotEmpty(message = "name should be not empty")
    private String name;

    @NotNull(message = "salary should be not empty")
    private Integer salary;
}
