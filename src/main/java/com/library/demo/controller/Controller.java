package com.library.demo.controller;

import com.library.demo.domain.Book;
import com.library.demo.service.BookService;
import io.micrometer.core.instrument.binder.http.HttpRequestTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    BookService service;

    @GetMapping
    @RequestMapping("/{id}")
public ResponseEntity<Book> getBookById(@PathVariable("id")  Long id){
       Book book= service.findById(id);
        return ResponseEntity.ok(book);
    }

@GetMapping
public ResponseEntity<List<Book>> getAll(){
    List<Book>list =service.getAllBooks();
    return ResponseEntity.ok(list);
}
@PostMapping("/new")
    public ResponseEntity<Map<String,String>> createBook(@RequestBody @Valid Book book){
    service.createBook(book);
    Map<String ,String>map=new HashMap<>();
    map.put("message","Book has been saved...");
    map.put("status","created");
    return ResponseEntity.ok(map);
}






}
