import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CineTest {
    boolean[] asientos;
    int solicitados;
    Cine cine;

    @BeforeEach void initial(){
        cine = new Cine();
    }
    @Test
    public void reservaButacasC1(){
        asientos = new boolean[0];
        solicitados = 3;
        ButacasException exception = assertThrows(ButacasException.class, () -> cine.reservaButacasV1(asientos, solicitados));
        assertEquals("No se puede procesar la solicitud", exception.getMessage());
    }

    @Test
    public void reservaButacasC2(){
        boolean asientos[] = {};
        solicitados = 0;
        boolean asientosEsperados[] = {};
        boolean reserva =
                assertDoesNotThrow(
                        ()-> cine.reservaButacasV1(asientos,solicitados), "Ha saltado la excepcion");
        assertAll(
                () -> assertFalse(reserva),
                ()-> assertArrayEquals(asientosEsperados , asientos)
        );
    }

    @Test
    public void reservaButacasC3(){
        solicitados = 2;
        asientos = new boolean[]{false, false, false, true, true};
        boolean[] asientosEsperados = new boolean[]{true, true, false, true, true};
        boolean reserva = assertDoesNotThrow(()-> cine.reservaButacasV1(asientos,solicitados), "Ha saltado la excepcion");
        assertAll(
                () -> assertEquals(true, reserva),
                ()-> assertArrayEquals(asientosEsperados , asientos)
        );
    }

    @Test
    public void reservaButacasC4(){
        solicitados = 1;
        asientos = new boolean[]{true, true, true};
        boolean[] asientosEsperados = new boolean[]{true, true, true};
        assertAll(
                () -> assertEquals(false,  assertDoesNotThrow(()-> cine.reservaButacasV1(asientos,solicitados), "Ha saltado la excepcion")),
                ()-> assertArrayEquals(asientosEsperados , asientos)
        );
    }
}
