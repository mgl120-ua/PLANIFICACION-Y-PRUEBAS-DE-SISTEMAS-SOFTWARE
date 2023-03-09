import java.time.LocalDate;

public class CalendarioStub extends Calendario{
    public boolean es_festivo(LocalDate otroDia) throws CalendarioException {
        String fecha = otroDia.toString();
        if(fecha.equals("2023-06-20") || fecha.equals("2023-06-24")){
            return true;
        }else if(fecha.equals("2023-04-18") || fecha.equals("2023-04-21") || fecha.equals("2023-04-22")){
            throw new CalendarioException();
        }else{
            return false;
        }
    }
}
