import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlquilaCochesTest {
    @Test
    public void calculaPrecioC1() throws MensajeException {
        AlquilarCochesTestable sut = new AlquilarCochesTestable();
        sut.calendario = new CalendarioStub();
        ServicioStub servicio = new ServicioStub();
        sut.setServicio(servicio);
        Ticket esperado = new Ticket();
        esperado.setPrecio_final(75);
        LocalDate fecha = LocalDate.of(2023, 05, 18);
        Ticket real = sut.calculaPrecio(TipoCoche.TURISMO, fecha, 10);
        assertEquals(esperado.getPrecio_final(), real.getPrecio_final());
    }

    @Test
    public void calculaPrecioC2() throws MensajeException {
        AlquilarCochesTestable sut = new AlquilarCochesTestable();
        sut.calendario = new CalendarioStub();
        ServicioStub servicio = new ServicioStub();
        sut.setServicio(servicio);
        Ticket esperado = new Ticket();
        esperado.setPrecio_final(62.5f);
        LocalDate fecha = LocalDate.of(2023, 06, 19);
        Ticket real = sut.calculaPrecio(TipoCoche.CARAVANA, fecha, 7);
        assertEquals(esperado.getPrecio_final(), real.getPrecio_final());
    }

    @Test
    public void calculaPrecioC3() throws MensajeException {
        AlquilarCochesTestable sut = new AlquilarCochesTestable();
        sut.calendario = new CalendarioStub();
        ServicioStub servicio = new ServicioStub();
        sut.setServicio(servicio);
        LocalDate fecha = LocalDate.of(2023, 04, 17);
        MensajeException exception = assertThrows(MensajeException.class, () -> sut.calculaPrecio(TipoCoche.TURISMO, fecha, 8));
        assertEquals("Error en dia: 2023-04-18; Error en dia: 2023-04-21; Error en dia: 2023-04-22; ", exception.getMessage());
    }
}