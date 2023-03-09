import ppss.Reserva;
import ppss.Usuario;

public class ReservaTestable extends Reserva {
    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu){
        if(login == "ppss" && password == "ppss" && tipoUsu == Usuario.BIBLIOTECARIO){
            return true;
        }else{
            return false;
        }
    }
}
