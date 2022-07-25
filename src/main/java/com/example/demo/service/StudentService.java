package com.example.demo.service;

import com.example.demo.mail.studentMailComponet;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;
    private studentMailComponet studentMailComponet;


    public Student save(Student student) {
        this.studentRepository.save(student);
        this.studentMailComponet.sendSimpleWelcomeEmail(student);
        //Enviar Email
        return student;
    }

}
