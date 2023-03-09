public class AlquilarCochesTestable extends AlquilaCoches{
    private IService servicio;
    public void setServicio(IService servicio){
        this.servicio = servicio;
    }
     public IService getServicio(){
        return servicio;
     }
}
