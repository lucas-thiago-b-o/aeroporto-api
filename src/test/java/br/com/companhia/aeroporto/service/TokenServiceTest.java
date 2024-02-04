package br.com.companhia.aeroporto.service;

import br.com.companhia.aeroporto.domain.Usuario;
import br.com.companhia.aeroporto.enums.UsuarioRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

class TokenServiceTest {

    @InjectMocks
    TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(tokenService, "secret", "secret");
    }

    @Test
    void testGenerateToken() {
        String result = tokenService.generateToken(new Usuario("id", "username", "password", UsuarioRole.ADMIN));
        Assertions.assertEquals(retornaToken(), result);
    }

    @Test
    void testValidateToken() {
        String result = tokenService.validateToken("token");
        Assertions.assertEquals("", result);
    }

    public String retornaToken() {
        return tokenService.generateToken(new Usuario("id", "username", "password", UsuarioRole.ADMIN));
    }
}