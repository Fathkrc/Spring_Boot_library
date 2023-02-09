package com.library.demo.DTO;

import com.library.demo.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "can not be blank")
    private String name;
    @NotBlank(message = "can not be blank")

    private String lastName;
    @NotBlank(message = "Provide valid UserName")
    @Size(min = 5,max = 10,message = "Please provide a UserName min 5 max 10 characters")
    private String userName;

    @NotBlank(message = "Provide valid UserName")
    @Size(min = 5,max = 20,message = "Please provide a password min 5 max 20 characters")
    private String password;

}
