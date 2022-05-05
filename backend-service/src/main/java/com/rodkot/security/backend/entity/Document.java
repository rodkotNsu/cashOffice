package com.rodkot.security.backend.entity;

import com.rodkot.security.backend.TypeDocument;
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
    Long id;

    @Column
    @Enumerated(EnumType.STRING)
    TypeDocument typeDocument;

    @Column
    String name;

    @Column
    String path;

    @Column
    OffsetDateTime createDataTime;
}
