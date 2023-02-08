package com.library.demo.controller;

import com.library.demo.DTO.BookDTO;
import com.library.demo.domain.Book;
import com.library.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Controller {
    /*
Yalnızca Service katımızı bağımlı hale getirdik repository ile direkt bir bağ yok .
yapımız katman katman ilerliyor .
verimizin bulunamaması gibi durumları service katında handle ediyoruz controller yalnızca endPointleri karşılayıp
yönlendirmemizi sağlıyor.
Mesela update methodumuzu yalnızca update ile çağırdık ve parametre olarak BookDTO proxy classımızı gönderdik
*/
    Logger logger= LoggerFactory.getLogger(Controller.class);
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
    map.put("message2","oglusum kafan nasıl da minik");
    return ResponseEntity.ok(map);
}
@PutMapping("{id}")
    public ResponseEntity<Map<String,String>> updateBook(@PathVariable("id") Long id,
                                                         @RequestBody BookDTO bookDTO){
        service.updateBook(id,bookDTO);
        Map<String , String>map=new HashMap<>();
        map.put("message","Book has been updated...");
        map.put("status","updated");
       return new ResponseEntity<>(map,HttpStatus.OK);
}
    @GetMapping("/page")
    public ResponseEntity<Page<Book>> getAllWithPage(@RequestParam("page") int page,
                                                     @RequestParam("size") int size,
                                                     @RequestParam("sort") String prop
                                                     ){
        Pageable pageble= PageRequest.of( page ,size, Sort.by(prop));
        //Spring frameworkden Pageble yapısı ile parametrelerimizi verdik ve bir obje çekip setledik.
        Page<Book> pages=service.getAllWithPage(pageble);
        return ResponseEntity.ok(pages);


    }
/*
Hatırlatma: ResponseEntity.ok(obje) methoodunu da kullanabilirdik bu
methodda newleme iştemi yapmıyoruz gibi görünse de methodun içeriğinde
kendi newleme mekanizması mevcut. Map ile frontend kısmına iletilecek mesaj ve status
durumumuzu oluşturduk ve ResponseEntity içerisinde tanımladık. 2. Olarak da Httpnin hazır
enum yapılarından .OK u seçtik ve R.E içerine yerleştirdik.
Controller classında service bean imiz üzerinden işaret ettiğimiz deleteEntity methodumuzu
Service katmanına gidip oluşturuyoruz .
 */

@DeleteMapping("{id}")
public ResponseEntity<Map<String,String>> deleteBook(@PathVariable("id") Long id){
    Map<String , String>map=new HashMap<>();
    map.put("message"," Book has been deleted...");
    map.put("status","deleted");
    service.deleteBook(id);
    return ResponseEntity.ok(map);
}

@GetMapping("/writerName")
    public ResponseEntity<List<Book>> getBooksByWriter(@RequestParam("writer") @Valid String writer){
    List<Book> list=service.findByWriter(writer);
    return ResponseEntity.ok(list);
// burada kitapları yazar ismi ile çağıracak bir kod çağırdık ve Service katında methodumuzu oluşturduk.

}
//kitap adı ile sorguu yapalım
    @GetMapping("/Page/{numberOfPage}")
    public ResponseEntity<List<Book>> getBookByMoreThanPage(@PathVariable("numberOfPage") Long numberOfPage){
    List<Book>list=service.getAllBookGreaterThan(numberOfPage);
    return ResponseEntity.ok(list);
//gönderilen sayfadan fazla sayfa sayısı olan kitapları getiren bir method yazdık.
        // JPQL kullandık repistory katmanına bakalım

    }
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Book>> getBookByType(@PathVariable("type") String type){
    List<Book>list=service.getBookByType(type);
    return ResponseEntity.ok(list);
    }
// databaseimizden dto verisi çekelim hadi bakalım
    @GetMapping("/bookDTO")// /bookDTO?id=1 yapısı
    public  ResponseEntity<BookDTO> getBookDTOS(@RequestParam("id") Long id){
    BookDTO bookDto=service.findBookDtoById(id);
    return ResponseEntity.ok(bookDto);

    }

    @GetMapping("welcome")
    public String welcome(HttpServletRequest request){// http://localhost:8080/hi
    logger.warn("------------------------ Welcome {}",request.getServletPath());
    //üstteki warn intelijdeki konsolumuza gidiyor.
    return "You are welcome to Library";
    //süslü parantez içine parametre gireceğiz
        // getServletPath ile servletin yolunu görmek istiyoruz
    // HttpServletRequest interface i gelen requeste ulaşabilmemizi sağlıyor
    }

}
