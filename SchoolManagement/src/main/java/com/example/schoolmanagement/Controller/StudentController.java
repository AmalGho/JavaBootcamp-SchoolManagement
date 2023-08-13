package com.example.schoolmanagement.Controller;

import com.example.schoolmanagement.ApiResponse.ApiResponse;
import com.example.schoolmanagement.Model.Student;
import com.example.schoolmanagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/get")
    public ArrayList<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student Added Successfully!"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        boolean isUpdated = studentService.updateStudent(id, student);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student information updated Successfully!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("something went wrong!"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Student deleted Successfully!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("something went wrong!!"));
    }


    @GetMapping("/getStudent/{name}")
    public ResponseEntity getStudentByName(@PathVariable String name) {
        Student student = studentService.getStudentByName(name);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("this student does not exist!"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

}
