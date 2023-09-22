package com.example.demo.controller;

import com.example.demo.entity.StudentIdCard;
import com.example.demo.dao.IdCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/card")
public class IdCardJPAController {

    @Autowired
    IdCardRepository idCardRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public IdCardJPAController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/save")
    public String saveCard(@RequestBody String jsonString){

        try {
            StudentIdCard studentIdCard = objectMapper.readValue(jsonString, StudentIdCard.class);
            idCardRepository.save(studentIdCard);
        } catch (Exception e) {
            return "save failed";
        }
        return "save successfully";
    }


    @DeleteMapping("/deleteIdCard/{id}")
    public String deleteIdCard(@PathVariable(value="id") Long id){
        try {
//            idCardRepository.deleteById(id);
            idCardRepository.deleteByIdNew(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "delete failed!";
        }
        return "delete successfully";
    }

    /**
     * 删除学生和学生卡之间的关联关系（关系断掉之后就可以单独删除学生卡数据）<br></br>注：此时我们不开启CascadeType以及orphanRemoval=true
     */
    @RequestMapping("/deleteRelationship/{id}")
    public String deleteRelationShip(@PathVariable(value="id") Long id){
        try {
            StudentIdCard idCard = idCardRepository.findById(id).orElse(null);
            if (idCard != null){
                idCard.setStudent(null);
                idCardRepository.save(idCard);
            }
            String deleteURL = "http://localhost:8080/card/deleteIdCard/"+id;
            restTemplate.delete(deleteURL);
        } catch (Exception e) {
            e.printStackTrace();
            return "delete failed!";
        }
        return "delete successfully";
    }

    @GetMapping("/findIdCard")
    public void findIdCard(){
        StudentIdCard studentIdCard = idCardRepository.findById(1L).orElse(null);
        System.out.println(studentIdCard);
    }


}
