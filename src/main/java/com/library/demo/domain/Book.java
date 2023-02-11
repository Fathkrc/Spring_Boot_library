package com.library.demo.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


//@NoArgsConstructor
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Library")
@Data
public class Book {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(generator = "UUID")// hash id üretiyor altta da nasıl çalışacağı set ediliyor
    @GenericGenerator(name= "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String Id;

    @NotBlank(message = "Please don't use space")
    @NotNull(message = "name can not be empty")
    private String name;

    @NotBlank(message = "Please don't use space")
    @NotNull(message = "writer can not be empty")
    private String writer;

    @NotBlank(message = "Please don't use space")
    @NotNull(message = "Type can not be empty")
    private String type;

    @Pattern(regexp ="\\d{4}",message = "insert year format as 4 numbers like : 2023")
    private String publishYear;

    private Long numberOfPage;


}
