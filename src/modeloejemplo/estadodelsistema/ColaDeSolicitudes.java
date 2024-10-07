package modeloejemplo.estadodelsistema;

import java.util.LinkedList;
import java.util.Queue;

public class ColaDeSolicitudes {

	private Queue<Solicitud> cola;
	
	public ColaDeSolicitudes() {
		super();
		cola = new LinkedList<Solicitud>();
	}

	public void encolarSolicitud(Solicitud solicitudParaAgregar) {
		cola.add(solicitudParaAgregar);
	}

	public int getCantSolicitudesEsperando() {
		return cola.size();
	}

	public Solicitud getSolicitudSiguiente(){
		return cola.poll();
	}
	
}
