package ppss;

import ppss.excepciones.*;

import java.util.ArrayList;

public class Reserva {
    private FactoriaBOs fd;
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void setFactoria(FactoriaBOs fd){
        this.fd = fd;
    }
    public void realizaReserva(String login, String password, String socio, String [] isbns) throws ReservaException {
        ArrayList<String> errores = new ArrayList<String>();
        if(!compruebaPermisos(login, password, Usuario.BIBLIOTECARIO)) {//DEPENDENCIA
            errores.add("ERROR de permisos");
        } else {
            //FactoriaBOs fd = new FactoriaBOs();//NO TESTABLE
            IOperacionBO io = fd.getOperacionBO();//DEPENDENCIA
            try {
                for(String isbn: isbns) {
                    try {
                        io.operacionReserva(socio, isbn);//DEPENDENCIA
                    } catch (IsbnInvalidoException iie) {
                        errores.add("ISBN invalido" + ":" + isbn);
                    }
                }
            } catch (SocioInvalidoException sie) {
                errores.add("SOCIO invalido");
            } catch (JDBCException je) {
                errores.add("CONEXION invalida");
            }
        }
        if (errores.size() > 0) {
            String mensajeError = "";
            for(String error: errores) {
                mensajeError += error + "; ";
            }
            throw new ReservaException(mensajeError);
        }
    }
}
