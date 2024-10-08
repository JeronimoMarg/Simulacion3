package modeloejemplo.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.ListaDeEventos;
import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;

public class EventoTerminaProcesamiento extends Evento {

	private int servidorQueAtendia;
	private int beneficio;

	public EventoTerminaProcesamiento(double tiempoDeOcurrencia, int servidor, int beneficio) {
		super(tiempoDeOcurrencia);
		this.servidorQueAtendia = servidor;
		this.beneficio = beneficio;
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinasEjemplo libreria) {
		
		
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		//El beneficio solo se actualiza cuando termina el procesamiento (se cobra, digamos)
		contadoresEjemplo.actualizarBeneficios(beneficio);
		
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;
		
		if(modeloActual.haySolicitudesEnEspera()) {
			Solicitud solicitudAProcesar = modeloActual.obtenerSolicitud();

			// se asigna el servidor a la solicitud que se ha procesado
			int numeroServidor = modeloActual.atenderSolicitud(solicitudAProcesar);
			double duracionDelProcesamiento = calcularDuracionProcesamiento(solicitudAProcesar, libreria);

			//se calcula el beneficio para la solicitud
			int nuevoBeneficio = calcularBeneficio(solicitudAProcesar);

			//si se atiende se actualiza el contador estadistico de tiempo promedio de cliente en kiosko
			contadoresEjemplo.actualizarSumaTiempoClientes(getTiempoDeOcurrencia()-solicitudAProcesar.getTiempoDeArribo());

			EventoTerminaProcesamiento nuevoEvento = new EventoTerminaProcesamiento(duracionDelProcesamiento, numeroServidor, nuevoBeneficio);
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
		if(solicitud.getTipo() == true){
			beneficio += 850 * solicitud.getCantidad();
			beneficio -= 400 * solicitud.getCantidad();
		}else{
			beneficio += 1200 * solicitud.getCantidad();
			beneficio -= 600 * solicitud.getCantidad();
		}
		return beneficio;

	}

	private double calcularDuracionProcesamiento(Solicitud solicitudAProcesar, LibreriaDeRutinasEjemplo libreria) {

		double duracionDelProcesamiento;
		if(solicitudAProcesar.getTipo() == true){
			duracionDelProcesamiento = libreria.tiempoDeProcesamientoPanaderia(solicitudAProcesar.getCantidad());
		}else{
			duracionDelProcesamiento = libreria.tiempoDeProcesamientoBebidasSaludables(solicitudAProcesar.getCantidad());
		}
		return duracionDelProcesamiento;

	}

}
