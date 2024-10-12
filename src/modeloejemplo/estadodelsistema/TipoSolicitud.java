package modeloejemplo.estadodelsistema;

public enum TipoSolicitud {
    BEBIDA,
    PANADERIA;

    @Override
    public String toString(){
        switch(this){
            case BEBIDA:
                return "bebida";
            case PANADERIA:
                return "panaderia";
            default:
                return null;
        }
    }
}
