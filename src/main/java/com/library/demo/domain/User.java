package com.library.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.demo.domain.Enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(nullable = false,length = 25)
    private String name;
    @Column(nullable = false,length = 25)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false) //hash codumuz uzun olabilir lenghtimizi uzun tutuyoruz
    private String password;
/*
    @JoinTable(name = "user_roles",// Many to Many yapısı ile yeni bir tablo oluşturduk ve
    joinColumns = @JoinColumn(name = "user_id"), // hangi fieldları olmasını istediysek onları set ettik
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(fetch = FetchType.EAGER)//Userları çağırdığımızda rolleri de gelsin istiyoruz (lazy x eager)
    private Set<Role> roles=new HashSet<>();// rolleri tekrar atama yapmaması için set oluşturduk
    //ManyToMany yapısında yeni bir tablo oluşturuyor onun Columnlarını set ettik!!!
*/
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
