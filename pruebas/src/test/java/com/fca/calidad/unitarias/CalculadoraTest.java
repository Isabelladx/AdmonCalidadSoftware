package com.fca.calidad.unitarias;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculadoraTest {

    private Calculadora miCalculadora;

    @BeforeEach
    public void setUp() {
        miCalculadora = new Calculadora();
    }

    @ParameterizedTest
    @CsvSource({
        "5.0, 3.0, 8.0",
        "10.0, 20.0, 30.0",
        "1.5, 2.5, 4.0",
        "100.0, 250.0, 350.0"
    })
    public void testSumaPositivos(double operador1, double operador2, double resultadoEsperado) {
        // Act / Ejecución
        double resultadoEjecutado = miCalculadora.suma(operador1, operador2);

        // Assert / Verificación
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testSumaDosNumerosNegativos() {
        // Arrange
        double operador1 = -4.0;
        double operador2 = -6.0;
        double resultadoEsperado = -10.0;

        // Act
        double resultadoEjecutado = miCalculadora.suma(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testSumaPositivoYNegativo() {
        // Arrange
        double operador1 = 10.0;
        double operador2 = -3.0;
        double resultadoEsperado = 7.0;

        // Act
        double resultadoEjecutado = miCalculadora.suma(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testSumaConCero() {
        // Arrange
        double operador1 = 9.5;
        double operador2 = 0.0;
        double resultadoEsperado = 9.5;

        // Act
        double resultadoEjecutado = miCalculadora.suma(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testSumaConDecimales() {
        // Arrange
        double operador1 = 2.25;
        double operador2 = 3.50;
        double resultadoEsperado = 5.75;

        // Act
        double resultadoEjecutado = miCalculadora.suma(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testSumaResultadoCero() {
        // Arrange
        double operador1 = 5.0;
        double operador2 = -5.0;
        double resultadoEsperado = 0.0;

        // Act
        double resultadoEjecutado = miCalculadora.suma(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testRestaDosNumerosPositivos() {
        // Arrange
        double operador1 = 10.0;
        double operador2 = 4.0;
        double resultadoEsperado = 6.0;

        // Act
        double resultadoEjecutado = miCalculadora.resta(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testRestaResultadoNegativo() {
        // Arrange
        double operador1 = 3.0;
        double operador2 = 8.0;
        double resultadoEsperado = -5.0;

        // Act
        double resultadoEjecutado = miCalculadora.resta(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testRestaDosNumerosNegativos() {
        // Arrange
        double operador1 = -10.0;
        double operador2 = -4.0;
        double resultadoEsperado = -6.0;

        // Act
        double resultadoEjecutado = miCalculadora.resta(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testRestaPositivoMenosNegativo() {
        // Arrange
        double operador1 = 5.0;
        double operador2 = -3.0;
        double resultadoEsperado = 8.0;

        // Act
        double resultadoEjecutado = miCalculadora.resta(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testRestaConCero() {
        // Arrange
        double operador1 = 7.5;
        double operador2 = 0.0;
        double resultadoEsperado = 7.5;

        // Act
        double resultadoEjecutado = miCalculadora.resta(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testRestaNumerosIguales() {
        // Arrange
        double operador1 = 9.0;
        double operador2 = 9.0;
        double resultadoEsperado = 0.0;

        // Act
        double resultadoEjecutado = miCalculadora.resta(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testRestaConDecimales() {
        // Arrange
        double operador1 = 5.75;
        double operador2 = 2.25;
        double resultadoEsperado = 3.50;

        // Act
        double resultadoEjecutado = miCalculadora.resta(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testDivideDosNumerosPositivos() {
        // Arrange
        double operador1 = 10.0;
        double operador2 = 2.0;
        double resultadoEsperado = 5.0;

        // Act
        double resultadoEjecutado = miCalculadora.divide(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testDivideDosNumerosNegativos() {
        // Arrange
        double operador1 = -20.0;
        double operador2 = -4.0;
        double resultadoEsperado = 5.0;

        // Act
        double resultadoEjecutado = miCalculadora.divide(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testDividePositivoEntreNegativo() {
        // Arrange
        double operador1 = 15.0;
        double operador2 = -3.0;
        double resultadoEsperado = -5.0;

        // Act
        double resultadoEjecutado = miCalculadora.divide(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testDivideResultadoDecimal() {
        // Arrange
        double operador1 = 5.0;
        double operador2 = 2.0;
        double resultadoEsperado = 2.5;

        // Act
        double resultadoEjecutado = miCalculadora.divide(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testDivideCeroEntreNumero() {
        // Arrange
        double operador1 = 0.0;
        double operador2 = 5.0;
        double resultadoEsperado = 0.0;

        // Act
        double resultadoEjecutado = miCalculadora.divide(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testDivideEntreCero() {
        // Arrange
        double operador1 = 10.0;
        double operador2 = 0.0;

        // Act
        double resultadoEjecutado = miCalculadora.divide(operador1, operador2);

        // Assert: En Java con double, dividir un número positivo entre cero produce Infinity
        assertThat(resultadoEjecutado).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    public void testDivideCeroEntreCero() {
        // Arrange
        double operador1 = 0.0;
        double operador2 = 0.0;

        // Act
        double resultadoEjecutado = miCalculadora.divide(operador1, operador2);

        // Assert: 0.0 / 0.0 en punto flotante resulta en NaN (Not a Number)
        assertThat(resultadoEjecutado).isNaN();
    }

    @Test
    public void testMultiplicaDosNumerosPositivos() {
        // Arrange
        double operador1 = 4.0;
        double operador2 = 5.0;
        double resultadoEsperado = 20.0;

        // Act
        double resultadoEjecutado = miCalculadora.multiplica(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testMultiplicaDosNumerosNegativos() {
        // Arrange
        double operador1 = -3.0;
        double operador2 = -6.0;
        double resultadoEsperado = 18.0;

        // Act
        double resultadoEjecutado = miCalculadora.multiplica(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testMultiplicaPositivoPorNegativo() {
        // Arrange
        double operador1 = 7.0;
        double operador2 = -2.0;
        double resultadoEsperado = -14.0;

        // Act
        double resultadoEjecutado = miCalculadora.multiplica(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testMultiplicaPorCero() {
        // Arrange
        double operador1 = 8.5;
        double operador2 = 0.0;
        double resultadoEsperado = 0.0;

        // Act
        double resultadoEjecutado = miCalculadora.multiplica(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testMultiplicaPorUno() {
        // Arrange
        double operador1 = 15.0;
        double operador2 = 1.0;
        double resultadoEsperado = 15.0;

        // Act
        double resultadoEjecutado = miCalculadora.multiplica(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testMultiplicaConDecimales() {
        // Arrange
        double operador1 = 2.5;
        double operador2 = 4.0;
        double resultadoEsperado = 10.0;

        // Act
        double resultadoEjecutado = miCalculadora.multiplica(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }

    @Test
    public void testMultiplicaConDecimalesPuros() {
        // Arrange
        double operador1 = 0.5;
        double operador2 = 0.5;
        double resultadoEsperado = 0.25;

        // Act
        double resultadoEjecutado = miCalculadora.multiplica(operador1, operador2);

        // Assert
        assertThat(resultadoEjecutado).isEqualTo(resultadoEsperado);
    }
}
