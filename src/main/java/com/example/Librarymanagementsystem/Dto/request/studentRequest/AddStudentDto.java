package com.example.Librarymanagementsystem.Dto.request.studentRequest;

import com.example.Librarymanagementsystem.enums.Department;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddStudentDto {
    private String name;
    private int age;
    private String mobNo;
    private Department department;
}
