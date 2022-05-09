package com.rodkot.security.backend.entity;

import com.rodkot.security.backend.exception.InsufficientFundsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.OffsetDateTime;
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
    private Long id;

    @ManyToOne
    private Organization organization;

    @Column
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
