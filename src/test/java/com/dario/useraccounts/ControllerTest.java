package com.dario.useraccounts;

import com.dario.useraccounts.controller.JwtAuthenticationController;
import com.dario.useraccounts.model.Phone;
import com.dario.useraccounts.model.User;
import com.dario.useraccounts.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class ControllerTest {

    @Autowired
    private JwtAuthenticationController controller;
    @Autowired
    private UserRepository userRepository;
    @MockBean
    private TestEntityManager entityManager;

    @Test
    @Transactional
    void createShouldReturnMessageFromService() throws Exception {

        Phone phone = Phone.builder().cityCode("SCL").countryCode("CL").build();
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        entityManager.persist(phone);
        User user = User.builder()
                .username("user")
                .password("aaaa1234AAAA")
                .created(new Date(System.currentTimeMillis()))
                .modified(new Date(System.currentTimeMillis()))
                .email("email@email.com")
                .isActive(true)
                .token(UUID.randomUUID().toString())
                .lastLogin(new Date(System.currentTimeMillis()))
                .phone(phones)
                .build();

        controller.createUser(user);
        User found = userRepository.findById(user.getUserid()).get();
        assertNotNull(found);
    }
}
