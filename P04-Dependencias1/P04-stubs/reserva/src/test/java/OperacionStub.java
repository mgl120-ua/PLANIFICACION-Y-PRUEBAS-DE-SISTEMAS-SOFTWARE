import ppss.IOperacionBO;
import ppss.Operacion;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class OperacionStub implements IOperacionBO {
    private String[] socios = {"Luis"};
    private String[] isbns = {"11111", "22222"};
    private boolean conexion = true;

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if(!Arrays.asList(socios).contains(socio)){
            throw new SocioInvalidoException();
        } if (!Arrays.asList(isbns).contains(isbn)) {
            throw new IsbnInvalidoException();
        } if (!conexion) {
            throw new JDBCException();
        }
    }

    public void setFalloConexion(boolean fallo){
        conexion = fallo;
    }
}