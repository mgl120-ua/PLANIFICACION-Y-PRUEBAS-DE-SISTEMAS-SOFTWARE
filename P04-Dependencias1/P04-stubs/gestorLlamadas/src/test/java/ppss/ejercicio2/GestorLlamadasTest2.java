package ppss.ejercicio2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorLlamadasTest2 {
    @Test
    public void calculaConsumoC1(){
        GestorLlamadasTestable2 sut = new GestorLlamadasTestable2();
        sut.setCalendario(new CalendarioStub2(15));
        assertEquals(sut.calculaConsumo(10),208);
    }

    @Test
    public void calculaConsumoC2(){
        GestorLlamadasTestable2 sut = new GestorLlamadasTestable2();
        sut.setCalendario(new CalendarioStub2(22));
        assertEquals(sut.calculaConsumo(10),105);
    }
}
