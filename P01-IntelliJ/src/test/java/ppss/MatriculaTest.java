package ppss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatriculaTest {
    int edad;
    boolean familiaNumerosa;
    boolean repetidor;
    float resultadoReal, resultadoEsperado;
    Matricula mat;

    @Test
    public void testCalculaTasaMatriculaC1() {
        edad = 23;
        familiaNumerosa = true;
        repetidor = true;
        resultadoEsperado = 250.00f;
        mat= new Matricula();
        resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        //el tercer parámetro del método Assert.assertEquals es necesario si estamos comparando "floats"
        //en este caso el método devuelve cierto si:
        //resultadoEsperado = resultadoReal +/- 0.002
        assertEquals(resultadoEsperado, resultadoReal,0.002f);
    }
}