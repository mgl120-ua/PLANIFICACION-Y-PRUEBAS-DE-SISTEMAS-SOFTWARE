import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("casosDePrueba")
    public void reservaButacasC5(boolean esperado, boolean[] asientosEsperados, boolean[] asientos, int solicitados){
        assertAll(
                () -> assertEquals(esperado,  assertDoesNotThrow(()-> cine.reservaButacasV1(asientos,solicitados), "Ha saltado la excepcion")),
                ()-> assertArrayEquals(asientosEsperados , asientos)
        );
    }

    private static Stream<Arguments> casosDePrueba(){
        boolean[] asientos2 = new boolean[0];
        boolean[] asientosEsperados2 = new boolean[0];
        boolean[] asientos3 = new boolean[]{false, false, false, true, true};
        boolean[] asientosEsperados3 = new boolean[]{true, true, false, true, true};
        boolean[] asientos4 = new boolean[]{true, true, true};
        boolean[] asientosEsperados4 = new boolean[]{true, true, true};

        return Stream.of(
            Arguments.of(false, asientosEsperados2, asientos2, 0),
            Arguments.of(true, asientosEsperados3, asientos3, 2),
            Arguments.of(false, asientosEsperados4, asientos4, 1)
        );
    }
}
