package com.library.demo.service;

import com.library.demo.DTO.BookDTO;
import com.library.demo.domain.Book;
import com.library.demo.exceptions.ConflictException;
import com.library.demo.exceptions.ResourceNotFoundExc;
import com.library.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class BookService {
    @Autowired
    Repository repistory;

    public List<Book> getAllBooks() {
        List<Book> list = repistory.findAll();
        return list;
    }

    public void createBook(Book book) {
        repistory.save(book);
    }

    public Book findById(Long id) {
        Book book = repistory.findById(id).orElseThrow(
                () -> new ResourceNotFoundExc("Book with " + id + " id does not exist")
        );
        return book;
    }

    public void updateBook(Long id, BookDTO bookDTO) {
        boolean isBookExist = repistory.existsById(id);


        Book book = repistory.findById(id).orElseThrow(
                () -> new ConflictException("there is not any book with " + id + " id")

        );
        book.setName(bookDTO.getName());
        book.setType(bookDTO.getType());
        book.setWriter(bookDTO.getWriter());
        book.setPublishYear(bookDTO.getPublishYear());
        book.setNumberOfPage(bookDTO.getNumberOfPage());
        repistory.save(book);
    }



    public void deleteBook(Long id) {
        if(!repistory.existsById(id)){
            throw new ConflictException("There is not any book with "+id+" id");
        }
        repistory.deleteById(id);
    }

    public Page<Book> getAllWithPage(Pageable page) {
        return repistory.findAll(page);
        //Pagebla yapısını direk spring çözümleyebiliyor içerisine Pageable set ettiğimiz değeri göndererek çalıştırdık
    }

    public List<Book> findByWriter(String writer) {
        return repistory.findByWriter(writer);
        //repistory katında find by kullanarak bir field ekledim ve Spring booot yapısı ne yapmak istediğimi algıladı
        //repistory katmanında bu isimle bir abstract method oluşturduk ve kodumuzu tamamladık.
        //olmayan bir istek yapılırsa boş entry geliyor JPA bu şekilde handle etmiş.


    }

    public List<Book> getAllBookGreaterThan(Long numberOfPage) {
       return repistory.getBooksBiggerThan(numberOfPage);
    }

    public List<Book> getBookByType(String type) {
        return repistory.getBooksWithType(type);
    }

    public BookDTO findBookDtoById(Long id) {
        return  repistory.findBookDtoById(id).orElseThrow(
                ()-> new ResourceNotFoundExc("Book not found with"+id+" id")
        );
    }
}
