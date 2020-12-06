package com.dario.useraccounts;

import static org.assertj.core.api.Assertions.assertThat;

import com.dario.useraccounts.controller.JwtAuthenticationController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {

    @Autowired
    private JwtAuthenticationController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}