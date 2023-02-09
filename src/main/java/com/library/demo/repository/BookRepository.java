package com.library.demo.repository;

import com.library.demo.DTO.BookDTO;
import com.library.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book , Long> {

    boolean existsByName(String name);

    List<Book> findByWriter(String writer);

    @Query("SELECT s FROM Book s WHERE  s.numberOfPage > :pages")
    List<Book> getBooksBiggerThan(@Param("pages") Long numberOfPage);
    //JPQL kullandık bunu kullanırken de Query içerisinde tablo adı değil Entity class ismi yazıyoruz

    //şimdi Native Sql ile bir query yazacağız yazacağız
    @Query(value="SELECT * FROM library s  WHERE s.type=:type " , nativeQuery = true)
    List<Book> getBooksWithType(@Param("type") String type);

    //kitapları türüne göre çağıran bir method oluşturduk.
    @Query("SELECT new com.library.demo.DTO.BookDTO(s) FROM Book s WHERE s.id=:id")
    Optional<BookDTO> findBookDtoById(@Param("id") Long id);
    //id ile BookDto objesi gönderdik :D:D
    //JPQL içerisinde newleme işlemi yaptık BookDTO constr. içerisine idli Book objemizi gönderdik

}
