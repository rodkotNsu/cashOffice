package com.rodkot.security.backend.entity;

import com.rodkot.security.backend.exception.InsufficientFundsException;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
public class Cash {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Organization organization;

    @Column(unique = true)
    private String name;

    @Column
    private Long money;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Operation> operations = new ArrayList<>();

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<User> allowUsers = new ArrayList<>();

    @Column
    private OffsetDateTime dateTimeCreated;

    public void runOperation(Operation operation) throws InsufficientFundsException {
        operation.getOperationAction().run(this, operation);
    }
}
