package com.example.Librarymanagementsystem.service;

import com.example.Librarymanagementsystem.Dto.request.studentRequest.AddStudentDto;
import com.example.Librarymanagementsystem.Dto.request.studentRequest.UpdateNameDto;
import com.example.Librarymanagementsystem.Dto.request.studentRequest.DeleteStudentByIdDto;
import com.example.Librarymanagementsystem.Dto.response.studentResponse.GetStudentDto;
import com.example.Librarymanagementsystem.Dto.response.studentResponse.UpdateNameResponse;
import com.example.Librarymanagementsystem.exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    public String addStudent(AddStudentDto addStudentDto);
    public String deleteById(DeleteStudentByIdDto deleteStudentByIdDto) throws StudentNotFoundException;
    public UpdateNameResponse updateName(UpdateNameDto updateNameDto) throws StudentNotFoundException;
    public GetStudentDto findById(Integer studentId) throws StudentNotFoundException;
    public List<GetStudentDto> findAll();
}
