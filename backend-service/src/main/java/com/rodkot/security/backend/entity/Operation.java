package com.rodkot.security.backend.entity;

import com.rodkot.security.backend.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    @Enumerated(EnumType.STRING)
    TypeOperation typeOperation;

    @Column
    OffsetDateTime dateTime;

    @Column
    Long amount;

    @OneToOne
    Document audio;

    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Document> documents = new ArrayList<>();
}
