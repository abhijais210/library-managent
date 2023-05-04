package com.example.Librarymanagementsystem.controller;

import com.example.Librarymanagementsystem.Dto.request.studentRequest.AddStudentDto;
import com.example.Librarymanagementsystem.Dto.request.studentRequest.UpdateNameDto;
import com.example.Librarymanagementsystem.Dto.request.studentRequest.DeleteStudentByIdDto;
import com.example.Librarymanagementsystem.Dto.response.studentResponse.GetStudentDto;
import com.example.Librarymanagementsystem.Dto.response.studentResponse.UpdateNameResponse;
import com.example.Librarymanagementsystem.exceptions.StudentNotFoundException;
import com.example.Librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody AddStudentDto addStudentDto){
        return studentService.addStudent(addStudentDto);
    }
    @DeleteMapping("/delete-by-id")
    public String deleteById(@RequestBody DeleteStudentByIdDto deleteStudentByIdDto) throws StudentNotFoundException {
        return studentService.deleteById(deleteStudentByIdDto);
    }
    @PutMapping("/update-name")
    public UpdateNameResponse updateName(@RequestBody UpdateNameDto updateNameDto) throws StudentNotFoundException{
        return studentService.updateName(updateNameDto);
    }
    @GetMapping("/find-by-id")
    public GetStudentDto findById(@RequestParam Integer studentId) throws StudentNotFoundException {
        return studentService.findById(studentId);
    }
    @GetMapping("/find-all")
    public List<GetStudentDto> findAll(){
        return studentService.findAll();
    }
}
