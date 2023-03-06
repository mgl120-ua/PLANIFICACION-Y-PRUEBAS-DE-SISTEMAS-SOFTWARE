package ppss.ejercicio1;

import org.junit.jupiter.api.Test;
import ppss.GestorLlamadasStub;

import static org.junit.jupiter.api.Assertions.*;
public class GestorLlamadasTest {
   @Test
    public void calculaConsumoC1(){
        GestorLlamadas sut = new GestorLlamadas();
        GestorLlamadasStub stub = new GestorLlamadasStub();
        stub.setHora(15);
        double expectedResult = 208;
        double realResult = sut.calculaConsumo(10);
        assertEquals(expectedResult, realResult);
    }

    @Test
    public void calculaConsumoC2(){
        GestorLlamadas sut = new GestorLlamadas();
        GestorLlamadasStub stub = new GestorLlamadasStub();
        stub.setHora(22);
        double expectedResult = 105;
        double realResult = sut.calculaConsumo(10);
        assertEquals(expectedResult, realResult);
    }
}
