package com.example.demo.dao;

import com.example.demo.entity.Student;
import com.example.demo.entity.StudentIdCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set id = ?1 where id = ?2")
    int updateStudentId(Long newId, Long oldId);

}
