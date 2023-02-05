package com.library.demo.domain;

import lombok.*;

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
public class Book {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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
