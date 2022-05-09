package com.rodkot.security.backend.entity;

import com.rodkot.security.backend.entity.operation.TypeDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;

    @Column
    private String name;

    @Column
    private String path;

    @Column
    private OffsetDateTime createDataTime;
}
