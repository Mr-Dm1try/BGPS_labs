package com.bgps.labs.controllers;

import com.bgps.labs.daos.StudentJdbc;
import com.bgps.labs.models.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentJdbc _std;

    public StudentController(StudentJdbc mark) {
        _std = mark;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        return _std.get(id);
    }

    @GetMapping("/students/all")
    public List<Student> getAllStudents(){
        return _std.getAll();
    }

    @GetMapping("/students/byGroupId/{id}")
    public List<Student> getByGroupId(@PathVariable int id){
        return _std.getByGroupId(id);
    }

    @PostMapping("/students/new")
    public int addNewStudent(@RequestBody Student std){
        return _std.add(std);
    }

    @PostMapping("/students/update")
    public int updateStudent(@RequestBody Student std){
        return _std.update(std);
    }

    @DeleteMapping("/students/delete/{id}")
    public int deleteStudentById(@PathVariable int id){
        return _std.delete(id);
    }
}
