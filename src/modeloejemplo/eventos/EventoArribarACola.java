package modeloejemplo.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.ListaDeEventos;
import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;
import modeloejemplo.estadodelsistema.TipoSolicitud;;

public class EventoArribarACola extends Evento {

	public EventoArribarACola(double tiempoDeOcurrencia) {
		super(tiempoDeOcurrencia);
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinasEjemplo libreria) {
				
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;
		
		//Agendar el próximo arribo de solicitud.
		EventoArribarACola nuevoEvento = new EventoArribarACola(libreria.tiempoEntreArribosSolicitudes());	
		eventos.agregar(nuevoEvento);

		//cuando se llega a la cola se ve cuanta gente hay esperando y se actualiza el promedio de gente esperando
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		contadoresEjemplo.actualizarLongitudPromedioClientesEnCola(modeloActual.getCantidadEsperando());
		
		//Procesar este arribo, para lo cual es necesario generar la solicitud que acaba de arribar.
		Solicitud solicitudParaAgregar = new Solicitud();
		solicitudParaAgregar.setTiempoDeArribo(getTiempoDeOcurrencia());
		
		if(modeloActual.estaServidorOcupado()) {
			modeloActual.encolarSolicitud(solicitudParaAgregar);
		}
		else {
			//Se atiende la solicitud y despues se guarda el numero del servidor que la ha atendido
			int numeroServidor = modeloActual.atenderSolicitud(solicitudParaAgregar);
			double duracionDelProcesamiento = calcularDuracionProcesamiento(solicitudParaAgregar, libreria);
			
			//beneficio de la nueva solicitud que se va a atender
			int beneficio = calcularBeneficio(solicitudParaAgregar);

			EventoTerminaProcesamiento nuevoEventoAdicional = new EventoTerminaProcesamiento(duracionDelProcesamiento, numeroServidor, beneficio, solicitudParaAgregar.getTiempoDeArribo(),duracionDelProcesamiento);
			eventos.agregar(nuevoEventoAdicional);
		}

		
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
