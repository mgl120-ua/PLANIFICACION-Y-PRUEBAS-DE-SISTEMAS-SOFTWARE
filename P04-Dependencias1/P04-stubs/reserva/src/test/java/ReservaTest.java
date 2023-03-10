import org.junit.jupiter.api.Test;
import ppss.Factoria;
import ppss.excepciones.ReservaException;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {
    @Test
    public void realizaReservaC1(){
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setOperacion(stub);
        ReservaException exception = assertThrows(ReservaException.class, () -> sut.realizaReserva("xxxx","xxxx","Luis", new String[]{"11111"}));
        assertEquals("ERROR de permisos; ", exception.getMessage());
    }

    @Test
    public void realizaReservaC2(){
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setOperacion(stub);
        assertDoesNotThrow(()-> sut.realizaReserva("ppss","ppss","Luis", new String[]{"11111","22222"}), "Ha saltado la excepcion");
    }

    @Test
    public void realizaReservaC3(){
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setOperacion(stub);
        ReservaException exception = assertThrows(ReservaException.class, () -> sut.realizaReserva("ppss","ppss","Luis", new String[]{"11111","33333","44444"}));
        assertEquals("ISBN invalido:33333; ISBN invalido:44444; ", exception.getMessage());
    }

    @Test
    public void realizaReservaC4(){
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setOperacion(stub);
        ReservaException exception = assertThrows(ReservaException.class, () -> sut.realizaReserva("ppss","ppss","Pepe", new String[]{"11111"}));
        assertEquals("SOCIO invalido; ", exception.getMessage());
    }

    @Test
    public void realizaReservaC5(){
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setOperacion(stub);
        stub.setFalloConexion(false);
        ReservaException exception = assertThrows(ReservaException.class, () -> sut.realizaReserva("ppss","ppss","Luis", new String[]{"11111","22222"}));
        assertEquals("CONEXION invalida; ", exception.getMessage());
    }
}
