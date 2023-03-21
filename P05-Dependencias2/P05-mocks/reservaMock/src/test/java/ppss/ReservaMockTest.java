package ppss;

import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaMockTest {
    @Test
    public void realizaReservaC1(){
        IMocksControl ctrl = createStrictControl();
        Reserva sutMock = partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").mock(ctrl);

        expect(sutMock.compruebaPermisos("xxxx","xxxx", Usuario.BIBLIOTECARIO)).andReturn(false);

        ctrl.replay();

        ReservaException exception = assertThrows(ReservaException.class, () -> sutMock.realizaReserva("xxxx","xxxx","Pepe", new String[]{"11111"}));
        assertEquals("ERROR de permisos; ", exception.getMessage());

        ctrl.verify();
    }

    @Test
    public void realizaReservaC2(){
        IMocksControl ctrl = createStrictControl();
        Reserva sutMock = partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos").mock(ctrl);
        FactoriaBOs factoriaMock = ctrl.mock(FactoriaBOs.class);
        IOperacionBO operacionMock = ctrl.mock(IOperacionBO.class);
        sutMock.setFactoria(factoriaMock);

        expect(sutMock.compruebaPermisos("ppss","ppss", Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);
        assertDoesNotThrow(()-> {
                    operacionMock.operacionReserva("Pepe", "22222");
                    operacionMock.operacionReserva("Pepe", "33333");
        });

        ctrl.replay();

        assertDoesNotThrow(()->
                sutMock.realizaReserva("ppss","ppss","Pepe", new String[]{"22222","33333"})
        );

        ctrl.verify();
    }
    @Test
    public  void realizaReservaC3(){
        IMocksControl ctrl = createStrictControl();
        Reserva sutMock = partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos").mock(ctrl);
        FactoriaBOs factoriaMock = ctrl.mock(FactoriaBOs.class);
        IOperacionBO operacionMock = ctrl.mock(IOperacionBO.class);
        sutMock.setFactoria(factoriaMock);

        expect(sutMock.compruebaPermisos("ppss","ppss", Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);
        assertDoesNotThrow(()-> {
            operacionMock.operacionReserva("Pepe", "11111");
            expectLastCall().andThrow(new IsbnInvalidoException());
            operacionMock.operacionReserva("Pepe", "22222");
            operacionMock.operacionReserva("Pepe", "55555");
            expectLastCall().andThrow(new IsbnInvalidoException());
        });

        ctrl.replay();

        ReservaException exception = assertThrows(ReservaException.class, () -> sutMock.realizaReserva("ppss","ppss","Pepe", new String[]{"11111", "22222", "55555"}));
        assertEquals("ISBN invalido:11111; ISBN invalido:55555; ", exception.getMessage());

        ctrl.verify();
    }
    @Test
    public  void realizaReservaC4() {
        IMocksControl ctrl = createStrictControl();
        Reserva sutMock = partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos").mock(ctrl);
        FactoriaBOs factoriaMock = ctrl.mock(FactoriaBOs.class);
        IOperacionBO operacionMock = ctrl.mock(IOperacionBO.class);
        sutMock.setFactoria(factoriaMock);

        expect(sutMock.compruebaPermisos("ppss","ppss", Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);
        assertDoesNotThrow(()-> {
            operacionMock.operacionReserva("Luis", "22222");
            expectLastCall().andThrow(new SocioInvalidoException());
        });

        ctrl.replay();

        ReservaException exception = assertThrows(ReservaException.class, () -> sutMock.realizaReserva("ppss","ppss","Luis", new String[]{ "22222"}));
        assertEquals("SOCIO invalido; ", exception.getMessage());

        ctrl.verify();
    }

    @Test
    public  void realizaReservaC5() {
        IMocksControl ctrl = createStrictControl();
        Reserva sutMock = partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos").mock(ctrl);
        FactoriaBOs factoriaMock = ctrl.mock(FactoriaBOs.class);
        IOperacionBO operacionMock = ctrl.mock(IOperacionBO.class);
        sutMock.setFactoria(factoriaMock);

        expect(sutMock.compruebaPermisos("ppss","ppss", Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(factoriaMock.getOperacionBO()).andReturn(operacionMock);
        assertDoesNotThrow(()-> {
            operacionMock.operacionReserva("Pepe", "11111");
            expectLastCall().andThrow(new IsbnInvalidoException());
            operacionMock.operacionReserva("Pepe", "22222");
            operacionMock.operacionReserva("Pepe", "33333");
            expectLastCall().andThrow(new JDBCException());
        });

        ctrl.replay();

        ReservaException exception = assertThrows(ReservaException.class, () -> sutMock.realizaReserva("ppss","ppss","Pepe", new String[]{"11111", "22222", "33333"}));
        assertEquals("ISBN invalido:11111; CONEXION invalida; ", exception.getMessage());

        ctrl.verify();
    }
}