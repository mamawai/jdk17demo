package com.example.demo.controller;


import com.example.demo.entity.Book;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentIdCard;
import com.example.demo.dao.StudentRepository;
import com.example.demo.requestEntity.BatchStudentWithIdCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentJPAController {

    private static final Logger logger = LoggerFactory.getLogger(StudentJPAController.class);

    /**
     * StudentDAO
     */
    @Autowired
    StudentRepository studentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/save")
    public String saveStudent(@RequestBody String jsonString) {
        try {
            Student student = objectMapper.readValue(jsonString, Student.class);
            studentRepository.save(student);
        } catch (Exception e) {
            return "save failed!";
        }
        return "save successfully!";
    }

    /**
     * 批量更新学生
     */
    @PostMapping("/batchSaveWithId")
    public String batchSaveStudentAndIdCard(@RequestBody String jsonString) {
        try {
            BatchStudentWithIdCard batch = objectMapper.readValue(jsonString, BatchStudentWithIdCard.class);

            List<String> cardIds = batch.getCardIds();
            List<Student> students = batch.getStudents();

            Iterator<String> iterator = cardIds.iterator();
            students.forEach(student -> student.setStudentIdCard(new StudentIdCard(iterator.next(),student)));

            students.get(0)
                    .addBook(new Book("三国演义", LocalDateTime.now().minusDays(2)))
                    .addBook(new Book("红楼梦", LocalDateTime.now().minusDays(3)))
                    .addBook(new Book("西游记",LocalDateTime.now().minusDays(4)));
            students.get(1)
                    .addBook(new Book("水浒传",LocalDateTime.now().minusDays(5)));

            studentRepository.saveAll(students);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return "save failed!";
        }
        return "save successfully!";
    }

    /**
     * 更新学生id（当联级存储的时候报错）
     */
    @PutMapping("/updateStudent")
    public String updateStudent(@RequestBody String jsonString){
        int i;
        try {
            i = studentRepository.updateStudentId(20L, 2L);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return "save failed!";
        }
        return "update successfully! count: "+ i;
    }

    /**
     * 删除学生（当配置了联级删除或orphanRemoval=true时生效）
     */
    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value="id") Long id){
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return "delete failed!";
        }
        return "delete successfully";
    }

    @GetMapping("/findStudent")
    public void findStudent(){
        Student student = studentRepository.findById(1L).orElse(null);
        System.out.println(student);
    }

}
