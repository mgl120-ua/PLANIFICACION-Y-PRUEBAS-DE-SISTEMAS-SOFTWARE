package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

public class Factoria {
    private static IOperacionBO io = null;
    public  static IOperacionBO create(){
        if(io != null){
            return io;
        }else{
            return new Operacion();
        }
    }
    public static void setOperacion(IOperacionBO op){
        io = op;
    }
}
