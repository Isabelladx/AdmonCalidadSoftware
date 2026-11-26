package com.fca.calidad.unitarias;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DependencyTest {

    @Mock
    private SubDependency subDependency;

    private Dependency dependency;

    @BeforeEach
    public void setUp() {
        dependency = new Dependency(subDependency);
    }

    @Test
    public void testSuma() {
        // Arrange
        double operando1 = 15.0;
        double operando2 = 25.0;
        double resultadoEsperado = 40.0;

        // Act
        double resultadoEjecutado = dependency.suma(operando1, operando2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testGetClassName() {
        // Act
        String resultadoEjecutado = dependency.getClassName();

        // Assert
        assertThat(resultadoEjecutado).isEqualTo("Dependency");
    }

    @Test
    public void testGetSubdependencyClassName() {
        // Arrange: simulamos la respuesta del mock
        when(subDependency.getClassName()).thenReturn("SubDependency");

        // Act
        String resultadoEjecutado = dependency.getSubdependencyClassName();

        // Assert
        assertThat(resultadoEjecutado).isEqualTo("SubDependency");

        // Verify: verificamos que el método del mock fue invocado
        verify(subDependency).getClassName();
    }

    @Test
    public void testAddTwo() {
        // Arrange
        int numero = 8;
        int resultadoEsperado = 10;

        // Act
        int resultadoEjecutado = dependency.addTwo(numero);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testGetClassNameUpperCase() {
        // Act
        String resultadoEjecutado = dependency.getClassNameUpperCase();

        // Assert
        assertThat(resultadoEjecutado).isEqualTo("DEPENDENCY");
    }
}
