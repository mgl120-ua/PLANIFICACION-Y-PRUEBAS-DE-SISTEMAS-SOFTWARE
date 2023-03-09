package ppss.ejercicio1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorLlamadasTest1 {
   @Test
    public void calculaConsumoC1(){
        GestorLlamadas1 sut = new GestorLlamadas1();
        GestorLlamadasStub1 stub = new GestorLlamadasStub1();
        stub.setHora(15);
        double expectedResult = 208;
        double realResult = sut.calculaConsumo(10);
        assertEquals(expectedResult, realResult);
    }

    @Test
    public void calculaConsumoC2(){
        GestorLlamadas1 sut = new GestorLlamadas1();
        GestorLlamadasStub1 stub = new GestorLlamadasStub1();
        stub.setHora(22);
        double expectedResult = 105;
        double realResult = sut.calculaConsumo(10);
        assertEquals(expectedResult, realResult);
    }
}
