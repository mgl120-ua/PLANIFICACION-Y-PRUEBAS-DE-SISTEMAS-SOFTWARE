package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.partialMockBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PremioTest {
    @Test
    public  void compruebaPremioC1(){
        Premio sut = new Premio();

        IMocksControl ctrl = EasyMock.createStrictControl();
        Premio mock1 =  partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);
        ClienteWebService mock2 = ctrl.mock(ClienteWebService.class);

        EasyMock.expect(mock1.generaNumero()).andReturn(0.07f);
        Assertions.assertDoesNotThrow(()->
            EasyMock.expect(mock2.obtenerPremio()).andReturn("entrada final Champions")
        );

        ctrl.replay();

        String result = sut.compruebaPremio();
        assertEquals("Premiado con entrada final Champions", result);

        ctrl.verify();
    }

    @Test
    public  void compruebaPremioC2(){
        Premio sut = new Premio();

        IMocksControl ctrl = EasyMock.createStrictControl();
        Premio mock1 =  partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);
        ClienteWebService mock2 = ctrl.mock(ClienteWebService.class);

        EasyMock.expect(mock1.generaNumero()).andReturn(0.03f);
        Assertions.assertDoesNotThrow(()->
                EasyMock.expect(mock2.obtenerPremio()).andThrow(new ClienteWebServiceException())
        );

        ctrl.replay();

        String result = sut.compruebaPremio();
        assertEquals("No se ha podido obtener el premio", result);

        ctrl.verify();
    }
    @Test
    public  void compruebaPremioC3(){
        Premio sut = new Premio();

        Premio mock =  partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock();

        EasyMock.expect(mock.generaNumero()).andReturn(0.03f);

        EasyMock.replay(mock);

        String result = sut.compruebaPremio();
        EasyMock.verify(mock);
        assertEquals("Sin premio", result);
    }
}
