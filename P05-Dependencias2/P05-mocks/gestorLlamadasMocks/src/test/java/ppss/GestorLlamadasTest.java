package ppss;

import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;
import ppss.Calendario;
import ppss.GestorLlamadas;
import static org.easymock.EasyMock.partialMockBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorLlamadasTest {
    @Test
    public void GestorLlamadasC1(){
        GestorLlamadas sut = new GestorLlamadas();
        //Inicializamos
        IMocksControl ctrl = EasyMock.createStrictControl();
        GestorLlamadas mock1 =  partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").mock(ctrl);
        Calendario mock2 = ctrl.mock(Calendario.class);

        //Expectativas
        EasyMock.expect(mock1.getCalendario()).andReturn(mock2);
        EasyMock.expect(mock2.getHoraActual()).andReturn(10);

        //Listo
        ctrl.replay();

        //comparamos resultados
        double result = mock1.calculaConsumo(22);
        assertEquals(457.6, result, 0.001);

        //Verificamos
        ctrl.verify();
    }

    @Test
    public void GestorLlamadasC2(){
        GestorLlamadas sut = new GestorLlamadas();

        IMocksControl ctrl = EasyMock.createStrictControl();
        GestorLlamadas mock1 =  partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").mock(ctrl);
        Calendario mock2 = ctrl.mock(Calendario.class);

        EasyMock.expect(mock1.getCalendario()).andReturn(mock2);
        EasyMock.expect(mock2.getHoraActual()).andReturn(21);

        ctrl.replay();

        double result = mock1.calculaConsumo(13);
        assertEquals(136.5, result, 0.001);

        ctrl.verify();
    }
}
