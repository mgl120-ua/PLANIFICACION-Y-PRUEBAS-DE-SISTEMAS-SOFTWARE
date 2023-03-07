package ppss.ejercicio2;

import ppss.ejercicio1.GestorLlamadas1;

public class GestorLlamadasTestable2 extends GestorLlamadas1 {
    private Calendario c;
    public void setCalendario(Calendario cal){
        this.c = cal;
    }
    public Calendario getCalendario(){
        return c;
    }
}
