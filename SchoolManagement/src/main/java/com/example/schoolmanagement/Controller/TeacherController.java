package com.example.schoolmanagement.Controller;


import com.example.schoolmanagement.ApiResponse.ApiResponse;
import com.example.schoolmanagement.Model.Teacher;
import com.example.schoolmanagement.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @GetMapping("/get")
    public ArrayList<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        teacherService.addTeacher(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Teacher Added Successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable  Integer id, @RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        boolean isUpdated = teacherService.updateTeacher(id, teacher);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Teacher information updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("something went wrong!!"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable Integer id) {
        boolean isDeleted = teacherService.deleteTeacher(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Teacher Deleted Successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("something went wrong!!"));
    }


    @GetMapping("/getTeacher/{id}")
    public ResponseEntity getTeacherById(@PathVariable Integer id) {
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("this id does not exist!!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }
}
