import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FicheroTextoTest {
    String nombreFichero;
    FicheroTexto fic;

    @BeforeEach
    void initial(){
        fic = new FicheroTexto();
    }
    @Test
    public void contarCaracteresC1(){
        nombreFichero = "ficheroC1.txt";
        FicheroException exception = assertThrows(FicheroException.class, () -> fic.contarCaracteres(nombreFichero));
        assertEquals("ficheroC1.txt (No existe el archivo o el directorio)", exception.getMessage());
    }
    @Test
    public void contarCaracteresC2(){
        nombreFichero = "src/test/resources/ficheroCorrecto.txt";
        int contador = assertDoesNotThrow(()-> fic.contarCaracteres(nombreFichero), "Ha saltado la excepcion");
        assertEquals(3,contador);
    }
}
