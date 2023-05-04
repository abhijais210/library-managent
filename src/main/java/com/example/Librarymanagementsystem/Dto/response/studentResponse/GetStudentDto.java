package com.example.Librarymanagementsystem.Dto.response.studentResponse;

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
public class GetStudentDto {
    private int id;
    private String name;
    private int age;
    private String mobNo;
    private Department department;
}
