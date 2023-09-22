package com.example.demo.requestEntity;

import com.example.demo.entity.Student;
import lombok.Getter;

import java.util.List;

@Getter
public class BatchStudentWithIdCard {
    List<String> cardIds;

    List<Student> students;
}
