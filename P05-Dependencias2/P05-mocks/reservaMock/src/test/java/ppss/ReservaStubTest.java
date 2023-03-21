package ppss;

import org.easymock.IMocksControl;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ReservaStubTest {
    @Test
    public void realizaReservaStub1(){
        Reserva sutStub = partialMockBuilder(Reserva.class).addMockedMethod("compruebaPermisos").niceMock();

        expect(sutStub.compruebaPermisos("xxxx","xxxx", Usuario.BIBLIOTECARIO)).andReturn(false);

        replay();

        ReservaException exception = assertThrows(ReservaException.class, () -> sutStub.realizaReserva("xxxx","xxxx","Pepe", new String[]{"11111"}));
        assertEquals("ERROR de permisos; ", exception.getMessage());
    }

    @Test
    public void realizaReservaStub2() {
        Reserva sutStub = partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos").niceMock();
        FactoriaBOs factoriaStub = niceMock(FactoriaBOs.class);
        IOperacionBO operacionStub = niceMock(IOperacionBO.class);
        sutStub.setFactoria(factoriaStub);

        expect(sutStub.compruebaPermisos("ppss", "ppss", Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(factoriaStub.getOperacionBO()).andReturn(operacionStub);
        assertDoesNotThrow(() -> {
            operacionStub.operacionReserva("Pepe", "22222");
            operacionStub.operacionReserva("Pepe", "33333");
        });

        replay(sutStub, factoriaStub, operacionStub);

        assertDoesNotThrow(() ->
                sutStub.realizaReserva("ppss", "ppss", "Pepe", new String[]{"22222", "33333"})
        );
    }

    @Test
    public  void realizaReservaStub3(){
        Reserva sutStub = partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos").niceMock();
        FactoriaBOs factoriaStub = niceMock(FactoriaBOs.class);
        IOperacionBO operacionStub = niceMock(IOperacionBO.class);
        sutStub.setFactoria(factoriaStub);

        expect(sutStub.compruebaPermisos("ppss","ppss", Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(factoriaStub.getOperacionBO()).andReturn(operacionStub);
        assertDoesNotThrow(()-> {
            operacionStub.operacionReserva("Pepe", "11111");
            expectLastCall().andThrow(new IsbnInvalidoException());
            operacionStub.operacionReserva("Pepe", "22222");
            operacionStub.operacionReserva("Pepe", "55555");
            expectLastCall().andThrow(new IsbnInvalidoException());
        });

        replay(sutStub, factoriaStub, operacionStub);

        ReservaException exception = assertThrows(ReservaException.class, () -> sutStub.realizaReserva("ppss","ppss","Pepe", new String[]{"11111", "22222", "55555"}));
        assertEquals("ISBN invalido:11111; ISBN invalido:55555; ", exception.getMessage());

    }
    @Test
    public  void realizaReservaStub4() {
        Reserva sutStub = partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos").niceMock();
        FactoriaBOs factoriaStub = niceMock(FactoriaBOs.class);
        IOperacionBO operacionStub = niceMock(IOperacionBO.class);
        sutStub.setFactoria(factoriaStub);

        expect(sutStub.compruebaPermisos("ppss","ppss", Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(factoriaStub.getOperacionBO()).andReturn(operacionStub);
        assertDoesNotThrow(()-> {
            operacionStub.operacionReserva("Luis", "22222");
            expectLastCall().andThrow(new SocioInvalidoException());
        });

        replay(sutStub, factoriaStub, operacionStub);

        ReservaException exception = assertThrows(ReservaException.class, () -> sutStub.realizaReserva("ppss","ppss","Luis", new String[]{ "22222"}));
        assertEquals("SOCIO invalido; ", exception.getMessage());
    }

    @Test
    public  void realizaReservaStub5() {
        Reserva sutStub = partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos").niceMock();
        FactoriaBOs factoriaStub = niceMock(FactoriaBOs.class);
        IOperacionBO operacionStub = niceMock(IOperacionBO.class);
        sutStub.setFactoria(factoriaStub);

        expect(sutStub.compruebaPermisos("ppss","ppss", Usuario.BIBLIOTECARIO)).andReturn(true);
        expect(factoriaStub.getOperacionBO()).andReturn(operacionStub);
        assertDoesNotThrow(()-> {
            operacionStub.operacionReserva("Pepe", "11111");
            expectLastCall().andThrow(new IsbnInvalidoException());
            operacionStub.operacionReserva("Pepe", "22222");
            operacionStub.operacionReserva("Pepe", "33333");
            expectLastCall().andThrow(new JDBCException());
        });

        replay(sutStub, factoriaStub, operacionStub);

        ReservaException exception = assertThrows(ReservaException.class, () -> sutStub.realizaReserva("ppss","ppss","Pepe", new String[]{"11111", "22222", "33333"}));
        assertEquals("ISBN invalido:11111; CONEXION invalida; ", exception.getMessage());

    }
}
