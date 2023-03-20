package ppss;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FicheroTextoTest {
    @Test
    public void contarCaracteresC1(){
        IMocksControl ctrl = EasyMock.createStrictControl();
        FicheroTexto mock1 = EasyMock.partialMockBuilder(FicheroTexto.class).addMockedMethod("getFichero").mock(ctrl);
        FileReader mock2 = ctrl.mock(FileReader.class);

        Assertions.assertDoesNotThrow(()->{
            EasyMock.expect(mock1.getFichero("src/test/resources/ficheroC1.txt")).andReturn(mock2);
            EasyMock.expect(mock2.read()).andReturn(1).times(2).andThrow(new IOException("src/test/resources/ficheroC1.txt"));
        });

        ctrl.replay();

        FicheroException exception = assertThrows(FicheroException.class, () -> mock1.contarCaracteres("src/test/resources/ficheroC1.txt"));
        assertEquals("src/test/resources/ficheroC1.txt (Error al leer el archivo)", exception.getMessage());

        ctrl.verify();
    }
    
    @Test
    public void contarCaracteresC2(){
        IMocksControl ctrl = EasyMock.createStrictControl();
        FicheroTexto mock1 = EasyMock.partialMockBuilder(FicheroTexto.class).addMockedMethod("getFichero").mock(ctrl);
        FileReader mock2 = ctrl.mock(FileReader.class);

        Assertions.assertDoesNotThrow(()->{
            EasyMock.expect(mock1.getFichero("src/test/resources/ficheroC1.txt")).andReturn(mock2);
            EasyMock.expect(mock2.read()).andReturn(1).times(3).andReturn(-1);
            mock2.close();
        });

        EasyMock.expectLastCall().andThrow(new IOException("src/test/resources/ficheroC1.txt"));

        ctrl.replay();

        FicheroException exception = assertThrows(FicheroException.class, () -> mock1.contarCaracteres("src/test/resources/ficheroC1.txt"));
        assertEquals("src/test/resources/ficheroC1.txt (Error al cerrar el archivo)", exception.getMessage());

        ctrl.verify();
    }

}
