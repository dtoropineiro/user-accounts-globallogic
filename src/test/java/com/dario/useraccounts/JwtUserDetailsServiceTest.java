package com.dario.useraccounts;

import com.dario.useraccounts.exceptions.AttributeException;
import com.dario.useraccounts.exceptions.PermissionDeniedException;
import com.dario.useraccounts.service.JwtUserDetailsService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
@SpringBootTest
public class JwtUserDetailsServiceTest {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    void accessApiWithUserWithNoJWTTokenShouldThrowPermisionDenied(){

        try{
            jwtUserDetailsService.loadUserByJwtRequest("baduser");
        }
        catch(PermissionDeniedException re) {
            expectedEx.expect(PermissionDeniedException.class);
        }

    }
}
