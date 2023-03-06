package ppss;

import ppss.ejercicio1.GestorLlamadas;

public class GestorLlamadasStub extends GestorLlamadas {
    int horaActual;
    public void setHora(int hora){
        this.horaActual = hora;
    }
    public int getHoraActual(){
        return horaActual;
    }
}
