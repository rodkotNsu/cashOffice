package com.rodkot.security.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cash {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    Organization organization;

    @Column
    Long money;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    Collection<Operation> operations = new ArrayList<>();

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    Collection<User> allowUsers = new ArrayList<>();
}
