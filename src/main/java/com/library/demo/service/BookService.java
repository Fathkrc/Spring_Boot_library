package com.library.demo.service;

import com.library.demo.domain.Book;
import com.library.demo.exceptions.ResourceNotFoundExc;
import com.library.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    Repository repistory;
    public List<Book> getAllBooks() {
        List<Book>list=repistory.findAll();
        return list;
    }

    public void createBook(Book book) {

        repistory.save(book);
    }

    public Book findById(Long id) {
        Book book=repistory.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExc("Book with "+id+" id does not exist")
        );
        return book;
    }
}
