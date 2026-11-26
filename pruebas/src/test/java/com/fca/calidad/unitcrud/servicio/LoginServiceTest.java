package com.fca.calidad.unitcrud.servicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fca.calidad.unitcrud.dao.IDAOUsuario;
import com.fca.calidad.unitcrud.modelo.Usuario;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private IDAOUsuario daoMock;

    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        loginService = new LoginService(daoMock);
    }

    @Test
    public void testLoginExitoso() {
        // Arrange
        String email = "usuario@correo.com";
        String password = "password123";
        Usuario usuarioEsperado = new Usuario("Juan Perez", email, password);

        when(daoMock.findUserByEmail(email)).thenReturn(usuarioEsperado);

        // Act
        boolean resultado = loginService.login(email, password);

        // Assert
        assertThat(resultado).isTrue();
        verify(daoMock).findUserByEmail(email);
    }

    @Test
    public void testLoginPasswordIncorrecto() {
        // Arrange
        String email = "usuario@correo.com";
        String passwordCorrecto = "password123";
        String passwordErroneo = "passwordIncorrecto";
        Usuario usuarioEsperado = new Usuario("Juan Perez", email, passwordCorrecto);

        when(daoMock.findUserByEmail(email)).thenReturn(usuarioEsperado);

        // Act
        boolean resultado = loginService.login(email, passwordErroneo);

        // Assert
        assertThat(resultado).isFalse();
        verify(daoMock).findUserByEmail(email);
    }

    @Test
    public void testLoginUsuarioNoExiste() {
        // Arrange
        String email = "noexiste@correo.com";
        String password = "cualquierPassword";

        when(daoMock.findUserByEmail(email)).thenReturn(null);

        // Act
        boolean resultado = loginService.login(email, password);

        // Assert
        assertThat(resultado).isFalse();
        verify(daoMock).findUserByEmail(email);
    }
}
