package com.example.demo.dao;

import com.example.demo.entity.StudentIdCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IdCardRepository extends CrudRepository<StudentIdCard,Long> {

    @Modifying
    @Transactional
    @Query("delete from StudentIdCard as sc where sc.id = ?1")
    void deleteByIdNew(Long id);
}
