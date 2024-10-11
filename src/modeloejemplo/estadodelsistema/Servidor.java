package modeloejemplo.estadodelsistema;

/* Servidor en la nube. */

public class Servidor {

	int id;

	Boolean estaOcupado; /* false = libre / true = ocupado */
	Solicitud solicitudEnProcesamiento; /* Solicitud que está siendo retenida en el servidor. */

	public Servidor(boolean estado, int id) {
		super();
		estaOcupado = estado;
		solicitudEnProcesamiento = null;
		this.id = id;
	}

	public boolean getEstaOcupado() {
		return estaOcupado;
	}

	public void pasarAOcupado(Solicitud solicitud) {
		estaOcupado = true;
		solicitudEnProcesamiento = solicitud;
	}

	public void setEstaOcupado(boolean estado) {
		estaOcupado = estado;
	}
	
	public int getID(){
		return id;
	}
}
