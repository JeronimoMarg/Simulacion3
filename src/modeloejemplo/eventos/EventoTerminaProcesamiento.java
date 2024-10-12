package modeloejemplo.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.ListaDeEventos;
import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;
import modeloejemplo.estadodelsistema.TipoSolicitud;

public class EventoTerminaProcesamiento extends Evento {

	private int servidorQueAtendia;
	private int beneficio;
	private double tiempoDeArribo;
	private double tiempoDeAtencion;

	public EventoTerminaProcesamiento(double tiempoDeOcurrencia, int servidor, int beneficio) {
		super(tiempoDeOcurrencia);
		this.servidorQueAtendia = servidor;
		this.beneficio = beneficio;
	}

	public EventoTerminaProcesamiento(double tiempoDeOcurrencia, int servidor, int beneficio, double tiempoDeArribo) {
		super(tiempoDeOcurrencia);
		this.servidorQueAtendia = servidor;
		this.beneficio = beneficio;
		this.tiempoDeArribo = tiempoDeArribo;
	}

	public EventoTerminaProcesamiento(double tiempoDeOcurrencia, int servidor, int beneficio, double tiempoDeArribo, double tiempoDeAtencion) {
		super(tiempoDeOcurrencia);
		this.servidorQueAtendia = servidor;
		this.beneficio = beneficio;
		this.tiempoDeArribo = tiempoDeArribo;
		this.tiempoDeAtencion = tiempoDeAtencion;
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinasEjemplo libreria) {
		
		
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		//El beneficio solo se actualiza cuando termina el procesamiento (se cobra, digamos)
		contadoresEjemplo.actualizarBeneficios(beneficio);
		contadoresEjemplo.actualizarCantProcesadas();
		contadoresEjemplo.actualizarSumaTiempoClientes(getTiempoDeOcurrencia()-tiempoDeArribo);
		contadoresEjemplo.actualizarCantidadAtendidoPorServidor(servidorQueAtendia);
		contadoresEjemplo.actualizarCantidadTiempoProcesadoPorServidor(servidorQueAtendia, tiempoDeAtencion);
		
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;
		
		if(modeloActual.haySolicitudesEnEspera()) {
			Solicitud solicitudAProcesar = modeloActual.obtenerSolicitud();

			modeloActual.actualizarServidorDisponible(servidorQueAtendia);

			// se asigna el servidor a la solicitud que se ha procesado
			int numeroServidor = modeloActual.atenderSolicitud(solicitudAProcesar);
			double duracionDelProcesamiento = calcularDuracionProcesamiento(solicitudAProcesar, libreria);

			//se calcula el beneficio para la solicitud
			int nuevoBeneficio = calcularBeneficio(solicitudAProcesar);

			EventoTerminaProcesamiento nuevoEvento = new EventoTerminaProcesamiento(duracionDelProcesamiento, numeroServidor, nuevoBeneficio, solicitudAProcesar.getTiempoDeArribo(), duracionDelProcesamiento);
			eventos.agregar(nuevoEvento);	
		}
		else {
			modeloActual.actualizarServidorDisponible(servidorQueAtendia);
		}

	}

	public void setServidor(int numeroServidor){
			servidorQueAtendia = numeroServidor;
	}

	private int calcularBeneficio(Solicitud solicitud){

		int beneficio = 0;
		if(solicitud.getTipo().equals(TipoSolicitud.PANADERIA)){
			beneficio += 850 * solicitud.getCantidad();
			beneficio -= 400 * solicitud.getCantidad();
		}else if (solicitud.getTipo().equals(TipoSolicitud.BEBIDA)){
			beneficio += 1200 * solicitud.getCantidad();
			beneficio -= 600 * solicitud.getCantidad();
		}
		return beneficio;

	}

	private double calcularDuracionProcesamiento(Solicitud solicitudAProcesar, LibreriaDeRutinasEjemplo libreria) {

		double duracionDelProcesamiento=0.0;
		if(solicitudAProcesar.getTipo().equals(TipoSolicitud.PANADERIA)){
			duracionDelProcesamiento = libreria.tiempoDeProcesamientoPanaderia(solicitudAProcesar.getCantidad());
		}else if (solicitudAProcesar.getTipo().equals(TipoSolicitud.BEBIDA)){
			duracionDelProcesamiento = libreria.tiempoDeProcesamientoBebidasSaludables(solicitudAProcesar.getCantidad());
		}
		return duracionDelProcesamiento;

	}

}
