package modeloejemplo.estadodelsistema;

import des.EstadoDelSistema;

/* Colección de variables de estado necesarias para describir  el sistema en un punto en el tiempo. */

public class ModeloDelEjemplo extends EstadoDelSistema {
	
	private ColaDeSolicitudes cola;
	//private Servidor servidor;
	private Servidores servidores;
	//determinamos la cantidad de servidores que se tienen
	private static final int cantidadServidores = 2;
	
	public void inicializar() {
		cola = new ColaDeSolicitudes();
		servidores = new Servidores(cantidadServidores);
	}

	public void encolarSolicitud(Solicitud solicitudParaAgregar) {		
		System.out.println("\t\t-- El MODELO encola una solicitud de " + solicitudParaAgregar.getTipoString() + " (" + solicitudParaAgregar.getCantidad()  + " productos) ya que el servidor está ocupado.");
		cola.encolarSolicitud(solicitudParaAgregar);
	}

	public boolean estaServidorOcupado() {
		return servidores.estanTodosOcupados();
	}

	public int atenderSolicitud(Solicitud solicitudParaAgregar) {
		System.out.println("\t\t-- El MODELO atiende una solicitud del tipo " + solicitudParaAgregar.getTipoString() + " (" + solicitudParaAgregar.getCantidad()  + " productos)");
		Servidor servidorQueAtiende = servidores.getServidorLibre();
		servidorQueAtiende.pasarAOcupado(solicitudParaAgregar);
		return servidorQueAtiende.getID();
	}

	public boolean haySolicitudesEnEspera() {
		return (cola.getCantSolicitudesEsperando()>0);
	}

	public void actualizarServidorDisponible(int servidorQueAtendia) {
		System.out.println("\t\t-- El MODELO deja al servidor disponible ya que no hay solicitudes en espera.");
		servidores.setDisponible(servidorQueAtendia);
	}

	public Solicitud obtenerSolicitud() {
		return cola.getSolicitudSiguiente();
	}

	public int getCantidadEsperando(){
		return cola.getCantSolicitudesEsperando();
	}
	
}
