package modeloejemplo.estadodelsistema;

import java.util.ArrayList;

public class Servidores {

    private ArrayList<Servidor> listaServidores;

    public Servidores(int cantidad){
        super();
        listaServidores = new ArrayList<Servidor>();
        for(int i=0; i<cantidad; i++){
            listaServidores.add(new Servidor(false,i));
        }
    }

    public boolean estanTodosOcupados(){

        long cantidadServidoresLibres = listaServidores.stream().filter(s -> !s.getEstaOcupado()).count();
        
        if(cantidadServidoresLibres == 0){
            return true;
        }
        return false;
    }

    public Servidor getServidorLibre() {
        
        Servidor servidorLibre = listaServidores.stream().filter(s -> !s.getEstaOcupado()).findFirst().orElse(null);
        return servidorLibre;

    }

	public void setDisponible(int servidorQueAtendia) {
		Servidor servidor = listaServidores.stream().filter(s -> s.getID() == servidorQueAtendia).findAny().orElse(null);
        servidor.setEstaOcupado(false);
	}

}
