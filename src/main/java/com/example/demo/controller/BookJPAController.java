package com.example.demo.controller;

import com.example.demo.dao.BookRepository;
import com.example.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookJPAController {

    private final BookRepository bookRepository;

    public BookJPAController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @DeleteMapping("/deleteBooks/{bookIds}")
    public void deleteBooks(@PathVariable String bookIds){
        String[] ids = bookIds.split("_");
        for (String id : ids) {
            bookRepository.deleteById(Long.valueOf(id));
        }
    }

    @GetMapping("/findBooks")
    public void findBooks(){
        Book book = bookRepository.findById(2L).orElse(null);
        System.out.println(book);
    }
}
