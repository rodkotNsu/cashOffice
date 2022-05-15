package com.rodkot.security.backend.entity;

import com.rodkot.security.backend.entity.operation.TypeDocument;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;

    @Column
    private String name;

    @Column(unique = true)
    private String path;

    @Column
    private OffsetDateTime createDataTime;
}
