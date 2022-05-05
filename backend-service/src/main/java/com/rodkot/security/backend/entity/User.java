package com.rodkot.security.backend.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles= new ArrayList<>();

    @Column
    private String email;

    @Column
    private String fullName;
}
