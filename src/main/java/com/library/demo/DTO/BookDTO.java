package com.library.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.demo.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
    /*
    bu classımız bizim yapımızı daha sağlam kılıyor. Id fieldimizin üretilmesini Springe
    bıraktığımız için burada o field yok.controller tabakasından veri yapımızı bozabilecek
    endpointleri BookDTO parametremizle üretip Book obje fieldlarını sonrasında setliyoruz
    böylece bize id ile birlikte gelen bir request body ile Book update edilmesini engelledik.
    DTO neden kullanıyoruz??
    1- Güvenlik 2- Hız
     */


    @NotBlank(message = "Please don't use space")
    @NotNull(message = "name can not be empty")
    private String name;

    @NotBlank(message = "Please don't use space")
    @NotNull(message = "Type can not be empty")
    @JsonProperty("bookType")//json formatında giriş ismini değiştirdik db de ve java da isim aynı
    private String type;

    @NotBlank(message = "Please don't use space")
    @NotNull(message = "writer can not be empty")
    private String writer;

    @Pattern(regexp ="\\d{4}",message = "insert year format as 4 numbers like : 2023")
    private String publishYear;

    private Long numberOfPage;

    public BookDTO(Book book){
        this.name=book.getName();
        this.type=book.getType();
        this.writer=book.getWriter();
        this.publishYear=book.getPublishYear();
        this.numberOfPage=book.getNumberOfPage();
    }
}
