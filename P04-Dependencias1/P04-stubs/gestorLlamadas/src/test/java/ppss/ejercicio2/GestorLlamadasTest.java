package ppss.ejercicio2;

import org.junit.jupiter.api.Test;
import ppss.GestorLlamadasStub;
import ppss.ejercicio1.GestorLlamadas;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorLlamadasTest {
    @Test
    public void calculaConsumoC1(){
        ppss.ejercicio2.GestorLlamadas sut = new ppss.ejercicio2.GestorLlamadas();
        GestorLlamadasStub stub = new GestorLlamadasStub();
        stub.setHora(15);
        double expectedResult = 208;
        double realResult = sut.calculaConsumo(10, );
        assertEquals(expectedResult, realResult);
    }

    @Test
    public void calculaConsumoC2(){
        ppss.ejercicio2.GestorLlamadas sut = new  ppss.ejercicio2.GestorLlamadas();
        GestorLlamadasStub stub = new GestorLlamadasStub();
        stub.setHora(22);
        double expectedResult = 105;
        double realResult = sut.calculaConsumo(10, );
        assertEquals(expectedResult, realResult);
    }
}
