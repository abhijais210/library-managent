package com.example.Librarymanagementsystem.service.impl;

import com.example.Librarymanagementsystem.Dto.request.studentRequest.AddStudentDto;
import com.example.Librarymanagementsystem.Dto.request.studentRequest.DeleteStudentByIdDto;
import com.example.Librarymanagementsystem.Dto.request.studentRequest.UpdateNameDto;
import com.example.Librarymanagementsystem.Dto.response.studentResponse.GetStudentDto;
import com.example.Librarymanagementsystem.Dto.response.studentResponse.UpdateNameResponse;
import com.example.Librarymanagementsystem.entity.Card;
import com.example.Librarymanagementsystem.entity.Student;
import com.example.Librarymanagementsystem.enums.CardStatus;
import com.example.Librarymanagementsystem.exceptions.StudentNotFoundException;
import com.example.Librarymanagementsystem.repository.StudentRepository;
import com.example.Librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public String addStudent(AddStudentDto addStudentDto) {
        //for each student we also create student card
        Student student = new Student();
        student.setName(addStudentDto.getName());
        student.setAge(addStudentDto.getAge());
        student.setDepartment(addStudentDto.getDepartment());
        student.setMobNo(addStudentDto.getMobNo());

        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setValidTill("2024-06-30");
        card.setStudent(student);

        student.setCard(card);
        studentRepository.save(student);
        return "student added successfully";
    }

    @Override
    public String deleteById(DeleteStudentByIdDto deleteStudentByIdDto) throws StudentNotFoundException {
        try{
            Student student = studentRepository.findById(deleteStudentByIdDto.getId()).get();
            studentRepository.delete(student);
            return "student deleted successfully";
        }catch (Exception e){
            throw new StudentNotFoundException("student does not exists");
        }
    }

    @Override
    public UpdateNameResponse updateName(UpdateNameDto updateNameDto) throws StudentNotFoundException {
        try{
            Student student = studentRepository.findById(updateNameDto.getId()).get();
            student.setName(updateNameDto.getName());
            studentRepository.save(student);

            return new UpdateNameResponse(student.getName(),student.getId());
        }catch (Exception e){
            throw new StudentNotFoundException("student not exists");
        }
    }

    @Override
    public GetStudentDto findById(Integer studentId) throws StudentNotFoundException {
        try{
            Student student = studentRepository.findById(studentId).get();

            return new GetStudentDto(student.getId(), student.getName(),student.getAge(),
                    student.getMobNo(),student.getDepartment());

        }catch (Exception e){
            throw new StudentNotFoundException("student not Exists");
        }
    }

    @Override
    public List<GetStudentDto> findAll() {
        List<Student> studentList =  studentRepository.findAll();
        List<GetStudentDto> getStudentDtoList = new ArrayList<>();

        for(Student student : studentList){
            getStudentDtoList.add(new GetStudentDto(student.getId(), student.getName(),student.getAge(),
                    student.getMobNo(),student.getDepartment()));
        }
        return getStudentDtoList;
    }
}
