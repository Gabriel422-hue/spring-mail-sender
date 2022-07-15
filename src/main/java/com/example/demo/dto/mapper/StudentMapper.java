package com.example.demo.dto.mapper;


import com.example.demo.dto.StudentRequestDto;
import com.example.demo.model.Student;

public class StudentMapper {

    public static Student fromDto(StudentRequestDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        student.setBirthday(dto.getBirthday());

        return student;
    }

}