package com.rodkot.security.backend.entity;

import com.rodkot.security.backend.entity.operation.TypeOperation;
import com.rodkot.security.backend.entity.operation.ConfigureOperation;
import com.rodkot.security.backend.entity.operation.OperationAction;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    @Column
    private OffsetDateTime dateTime;

    @Column
    private Long amount;

    @Column
    private String comment;

    @OneToOne
    private Document audio;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Document> documents = new ArrayList<>();

    OperationAction getOperationAction(){
        return ConfigureOperation.getOperationAction(typeOperation);
    }
}
