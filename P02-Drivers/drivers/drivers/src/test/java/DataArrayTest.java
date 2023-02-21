import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
