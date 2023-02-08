package com.library.demo.domain;

import com.library.demo.domain.Enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Enumerated(EnumType.STRING)//tablomuzdan değerlerimiz integer olarak değil string olarak gelmesini istedik
    @Column(length = 30,nullable = false)
    private UserRole name;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
