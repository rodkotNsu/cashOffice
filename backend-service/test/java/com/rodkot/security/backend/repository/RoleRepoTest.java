package com.rodkot.security.backend.repository;

import com.rodkot.security.backend.entity.Role;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepoTest {
    @Autowired
    private RoleRepo roleRepo;

    @Test
    public void saveTest() {
        roleRepo.save(Role.builder().name("Role").id(1L).build());
    }
}