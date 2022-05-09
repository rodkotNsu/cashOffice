package com.rodkot.security.backend.repository;

import com.rodkot.security.backend.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<Document,Long> {

}
