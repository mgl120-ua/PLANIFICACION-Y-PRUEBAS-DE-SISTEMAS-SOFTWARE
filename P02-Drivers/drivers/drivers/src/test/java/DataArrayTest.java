import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DataArrayTest {
    @Test
    public void deleteC1(){
        int datos[] = {1,3,5,7};
        int esperado[] = {1,3,7};
        DataArray data = new DataArray(datos);
        assertDoesNotThrow(()-> data.delete(5), "Ha saltado la excepcion");
        assertArrayEquals(esperado, data.getColeccion());
        assertEquals(3,data.size());
    }

    @Test
    public void deleteC2(){
        int datos[] = {1,3,3,5,7};
        int esperado[] = {1,3,5,7};
        DataArray data = new DataArray(datos);
        assertDoesNotThrow(()-> data.delete(3), "Ha saltado la excepcion");
        assertArrayEquals(esperado, data.getColeccion());
        assertEquals(4,data.size());
    }

    @Test
    public void deleteC3(){
        int datos[] = {1,2,3,4,5,6,7,8,9,10};
        int esperado[] = {1,2,3,5,6,7,8,9,10};
        DataArray data = new DataArray(datos);
        assertDoesNotThrow(()-> data.delete(4), "Ha saltado la excepcion");
        assertArrayEquals(esperado, data.getColeccion());
        assertEquals(9,data.size());
    }

    @Test
    public void deleteC4(){
        int datos[] = {};
        DataArray data = new DataArray(datos);
        DataException exception = assertThrows(DataException.class, () -> data.delete(8));
        assertEquals("No hay elementos en la colección", exception.getMessage());
    }

    @Test
    public void deleteC5(){
        int datos[] = {1,3,5,7};
        DataArray data = new DataArray(datos);
        DataException exception = assertThrows(DataException.class, () -> data.delete(-5));
        assertEquals("El valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    public void deleteC6(){
        int datos[] = {};
        DataArray data = new DataArray(datos);
        DataException exception = assertThrows(DataException.class, () -> data.delete(0));
        assertEquals("Colección vacía. Y el valor a borrar debe ser > 0", exception.getMessage());
    }

    @Test
    public void deleteC7(){
        int datos[] = {1,3,5,7};
        DataArray data = new DataArray(datos);
        DataException exception = assertThrows(DataException.class, () -> data.delete(8));
        assertEquals("Elemento no encontrado", exception.getMessage());
    }
    @Tag("parametrizado")
    @Tag("conExcepciones")
    @ParameterizedTest
    @MethodSource("casosDePrueba1")
    void testParametrizadoC8(String esperado, DataArray data, int elem){
        DataException exception = assertThrows(DataException.class, () -> data.delete(elem));
        assertEquals(esperado, exception.getMessage());
    }
    @Tag("parametrizado")
    @Tag("conExcepciones")
    private static Stream<Arguments> casosDePrueba1() {
        int datos4[] = {};
        int datos5[] = {1, 3, 5, 7};
        int datos6[] = {};
        int datos7[] = {1,3,5,7};

        return Stream.of(
            Arguments.of("No hay elementos en la colección", new DataArray(datos4), 8),
            Arguments.of("El valor a borrar debe ser > 0", new DataArray(datos5), -5),
            Arguments.of("Colección vacía. Y el valor a borrar debe ser > 0", new DataArray(datos6), 0),
            Arguments.of("Elemento no encontrado", new DataArray(datos7), 8)
        );
    }

    @Tag("parametrizado")
    @ParameterizedTest
    @MethodSource("casosDePrueba2")
    void testParametrizadoC9(int[] esperado, int size, DataArray data, int elem){
        assertDoesNotThrow(()-> data.delete(elem), "Ha saltado la excepcion");
        assertArrayEquals(esperado, data.getColeccion());
        assertEquals(size,data.size());
    }

    @Tag("parametrizado")
    private static Stream<Arguments> casosDePrueba2() {
        int datos1[] = {1,3,5,7};
        int esperado1[] = {1,3,7};

        int datos2[] = {1,3,3,5,7};
        int esperado2[] = {1,3,5,7};

        int datos3[] = {1,2,3,4,5,6,7,8,9,10};
        int esperado3[] = {1,2,3,5,6,7,8,9,10};


        return Stream.of(
                Arguments.of(esperado1, 3, new DataArray(datos1), 5),
                Arguments.of(esperado2, 4, new DataArray(datos2), 3),
                Arguments.of(esperado3, 9, new DataArray(datos3), 4)
        );
    }
}
