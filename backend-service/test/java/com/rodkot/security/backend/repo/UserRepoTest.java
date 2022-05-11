package com.rodkot.security.backend.repo;


import com.rodkot.security.backend.Application;
import com.rodkot.security.backend.entity.Role;
import com.rodkot.security.backend.entity.User;
import com.rodkot.security.backend.repository.UserRepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = Application.class)
public class UserRepoTest {
    private static final String LOGIN1 = "login";
    private static final String LOGIN2 = "login2";
    private static final String PASSWORD = "password";
    private static final Role ROLE = new Role(null, "ROLE_ADMIN");
    private static final String EMAIL1 = "email@gmail.com";
    private static final String EMAIL2 = "email2@gmail.com";
    private static final String FULL_NAME1 = "fullName";
    private static final String FULL_NAME2 = "fullName2";


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

   @Test
   public void findByIdTest() {
        User savedClient = entityManager.persist(new User(1L, LOGIN1, PASSWORD, List.of(ROLE), EMAIL1, FULL_NAME1));
        entityManager.flush();

        User found = userRepo.findById(savedClient.getId())
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));

        assertEquals(savedClient, found);
    }

    @Test
   public void findAllTest() {
        entityManager.persist(new User(null, LOGIN1, PASSWORD, List.of(ROLE), EMAIL1, FULL_NAME1));
        entityManager.persist(new User(null, LOGIN2, PASSWORD, List.of(ROLE), EMAIL2, FULL_NAME2));
        entityManager.flush();

        List<User> found = userRepo.findAll();

        assertEquals(2, found.size());
    }

    @Test
    public void saveTest() {
        User user = new User(1L, LOGIN1, PASSWORD, List.of(ROLE), EMAIL1, FULL_NAME1);
        User savedClient = entityManager.persist(user);
        entityManager.flush();

        assertEquals(user, savedClient);
    }

    @Test
   public void getClientsByUsername() {
        User user1 = entityManager.persist(new User(null, LOGIN1, PASSWORD, List.of(ROLE), EMAIL1, FULL_NAME1));
        User user2 = entityManager.persist(new User(null, LOGIN2, PASSWORD, List.of(ROLE), EMAIL2, FULL_NAME2));
        entityManager.flush();

        User found = userRepo.findByUsername(user1.getUsername());

        assertEquals(user1,found);
    }
}
