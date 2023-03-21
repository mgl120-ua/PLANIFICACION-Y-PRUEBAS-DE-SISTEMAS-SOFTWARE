package ppss;

import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaTest {
    @Test
    public void realizaReservaC1(){
        IMocksControl ctrl = createStrictControl();
        Reserva sutMock = partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").mock(ctrl);
        Factoria FactoriaMock = ctrl.mock(Factoria.class);
        Operacion OperacionMock = ctrl.mock(Operacion.class);


        expect(sutMock.compruebaPermisos("xxxx","xxxx", Usuario.PROFESOR)).andReturn(false);

        ReservaException exception = assertThrows(ReservaException.class, () -> sutMock.realizaReserva("xxxx","xxxx","Pepe", new String[]{"11111"}));
        assertEquals("ERROR de permisos; ", exception.getMessage());



    }
}